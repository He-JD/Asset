package com.xgq.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: he_jd
 * @Description:
 * @Date: 2019-03-04
 */
@Data
@Component
@ConfigurationProperties(prefix = "asset.cache.redis")
public class RedisCachePropertie {

        /**
         * Database index used by the connection factory.
         */
        private int database = 0;

        /**
         * Redis url, which will overrule host, port and password if set.
         */
        private String url;

        /**
         * Redis server host.
         */
        private String host = "localhost";

        /**
         * Login password of the redis server.
         */
        private String password;

        /**
         * Redis server port.
         */
        private int port = 6379;

        /**
         * Enable SSL.
         */
        private boolean ssl;

        /**
         * Connection timeout in milliseconds.
         */
        private int timeout;
        /**
         * DEFAULT expire for redis cache
         * 0 never expire
         */
        private int defaultExpiry = -1;

        private String cacheNamePrefix;

        /**
         * Comma-separated list of cache names to create if supported by the underlying cache
         * manager. Usually, this disables the ability to create additional caches on-the-fly.
         */
        private List<String> cacheNames = new ArrayList<String>();

        private Pool pool;

        private Sentinel sentinel;

        private Cluster cluster;

        private HashMap<String, String> expires;
        /**
         * Pool properties.
         */
        @Data
        public static class Pool {

            /**
             * Max number of "idle" connections in the pool. Use a negative value to indicate
             * an unlimited number of idle connections.
             */
            private int maxIdle = 8;

            /**
             * Target for the minimum number of idle connections to maintain in the pool. This
             * setting only has an effect if it is positive.
             */
            private int minIdle = 0;

            /**
             * Max number of connections that can be allocated by the pool at a given time.
             * Use a negative value for no limit.
             */
            private int maxActive = 8;

            /**
             * Maximum amount of time (in milliseconds) a connection allocation should block
             * before throwing an exception when the pool is exhausted. Use a negative value
             * to block indefinitely.
             */
            private int maxWait = -1;


        }

        /**
         * Cluster properties.
         */
        @Data
        public static class Cluster {

            /**
             * Comma-separated list of "host:port" pairs to bootstrap from. This represents an
             * "initial" list of cluster nodes and is required to have at least one entry.
             */
            private List<String> nodes;

            /**
             * Maximum number of redirects to follow when executing commands across the
             * cluster.
             */
            private Integer maxRedirects;


        }

        /**
         * Redis sentinel properties.
         */
        @Data
        public static class Sentinel {

            /**
             * Name of Redis server.
             */
            private String master;

            /**
             * Comma-separated list of host:port pairs.
             */
            private String nodes;


        }

    }