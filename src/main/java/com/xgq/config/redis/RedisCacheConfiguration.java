package com.xgq.config.redis;


import com.xgq.common.NameResolvedCacheResolver;
import com.xgq.util.JudgeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import static org.apache.commons.lang3.math.NumberUtils.toLong;
import static com.xgq.util.JudgeUtils.isNotEmpty;

@Configuration
@ConditionalOnClass({ JedisConnection.class, Jedis.class })
@ConditionalOnProperty(prefix = "asset.cache.redis", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(RedisCacheProperties.class)
@Import({CacheRedisConfiguration.class})
public class RedisCacheConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    @Configuration
    public static class InternalRedisCacheConfiguration  {

        private static final int DEFAULT_EXPIRE_TIME = 600; //s
        private static final String DEFAULT_REDIS_CACHE_PREFIX = "CACHE";
        public static final String CACHE_NAME_PREFIX_SEPARATOR = ":";
  
        private RedisCacheProperties redisCacheProperties;
        
        public InternalRedisCacheConfiguration(RedisCacheProperties redisCacheProperties) {
            this.redisCacheProperties = redisCacheProperties;
        }
    


        @Bean
        public CacheManager redisCacheManager(JedisConnectionFactory cacheRedisConnectionFactory) {

            org.springframework.data.redis.cache.RedisCacheConfiguration config = org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofSeconds(getDefaultExpireTime())) // 设置缓存有效期10分钟
                    .disableCachingNullValues()
                    .computePrefixWith(cacheName -> getCacheNamePrefix())  ;
            //cache name 对应的缓存时间
            Map<String, Long> expires = parseRedisCacheExpires();
            Set<String> cacheNameSet = new HashSet<>();
            expires.forEach((k,v) -> cacheNameSet.add(k));
            Map<String, org.springframework.data.redis.cache.RedisCacheConfiguration> configMap = expires.entrySet().stream().
                    collect(Collectors.toMap(e -> e.getKey(), e -> config.entryTtl(Duration.ofSeconds(e.getValue()))));
            return RedisCacheManager
                            .builder(RedisCacheWriter.nonLockingRedisCacheWriter(cacheRedisConnectionFactory))
                            .initialCacheNames(cacheNameSet)
                            .withInitialCacheConfigurations(configMap)
                            .build();
        }

        @Bean
        public CacheResolver redisCacheResolver(CacheManager redisCacheManager) {

            return new NameResolvedCacheResolver(redisCacheManager, originalCacheName -> resolveCacheName(originalCacheName));
        }

        private int getDefaultExpireTime() {
            return redisCacheProperties.getDefaultExpiry() == -1 ? DEFAULT_EXPIRE_TIME : redisCacheProperties.getDefaultExpiry();
        }

        /**
         * 解析redis过期时间
         * @return
         */
        private Map<String, Long> parseRedisCacheExpires() {

            Map<String, Long> redisExpires = redisCacheProperties.getExpires().entrySet().stream()
                    .filter(sp -> isNotEmpty(sp.getKey()))
                    .collect(Collectors.toMap(
                            sp -> sp.getKey(),
                            sp -> toLong(String.valueOf(sp.getValue()))
                    ));
            return Collections.unmodifiableMap(redisExpires);
        }

        private String resolveCacheName(String cacheName) {
            StringBuilder cacheNameStringBuilder = new StringBuilder();
            String cacheNamePrefix = getCacheNamePrefix();
            if (JudgeUtils.isNotEmpty(cacheNamePrefix) && ! cacheName.startsWith(cacheNamePrefix)) {
                cacheNameStringBuilder.append(cacheNamePrefix).append(CACHE_NAME_PREFIX_SEPARATOR);
            }
            cacheNameStringBuilder.append(cacheName);
            return cacheNameStringBuilder.toString();
        }

        private String getCacheNamePrefix() {
            return Optional.ofNullable(this.redisCacheProperties.getCacheNamePrefix()).filter(JudgeUtils::isNotEmpty).orElse(DEFAULT_REDIS_CACHE_PREFIX);
        }


    }


}

