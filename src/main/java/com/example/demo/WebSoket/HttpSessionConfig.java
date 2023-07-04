//package com.example.demo.WebSoket;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisClusterConnection;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisSentinelConnection;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
//@Configuration
//@EnableRedisHttpSession
//public class HttpSessionConfig {
//    @Bean
//    public RedisConnectionFactory connectionFactory() {
//        return new RedisConnectionFactory() {
//            @Override
//            public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
//                return null;
//            }
//
//            @Override
//            public RedisConnection getConnection() {
//                return null;
//            }
//
//            @Override
//            public RedisClusterConnection getClusterConnection() {
//                return null;
//            }
//
//            @Override
//            public boolean getConvertPipelineAndTxResults() {
//                return false;
//            }
//
//            @Override
//            public RedisSentinelConnection getSentinelConnection() {
//                return null;
//            }
//        };
//    }
//}
