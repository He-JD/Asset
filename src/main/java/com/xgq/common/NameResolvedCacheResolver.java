package com.xgq.common;

import com.xgq.util.JudgeUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: he_jd
 * @Description:
 * @Date: 2019-03-06
 */
public class NameResolvedCacheResolver extends AbstractCacheResolver {
    private CacheNameResolver cacheNameResolver;

    public NameResolvedCacheResolver(CacheManager cacheManager) {
        this(cacheManager, null);
    }

    public NameResolvedCacheResolver(CacheManager cacheManager, CacheNameResolver cacheNameResolver) {
        super(cacheManager);
        this.cacheNameResolver = cacheNameResolver;
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = context.getOperation().getCacheNames();
        return JudgeUtils.isNotEmpty(cacheNames) ? cacheNames.stream().map(this::resolveCacheName).collect(Collectors.toList()) : cacheNames;
    }

    private String resolveCacheName(String originalCacheName) {
        return Optional.ofNullable(this.cacheNameResolver).map(r -> r.resolve(originalCacheName)).orElse(originalCacheName);
    }
}
