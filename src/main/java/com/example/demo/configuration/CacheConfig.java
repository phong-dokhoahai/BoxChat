package com.example.demo.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching // Kích hoạt tính năng caching
public class CacheConfig {
    // Cấu hình cache manager (ví dụ: sử dụng EhCache)
//    @Bean
//    public CacheManager cacheManager() {
//        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
//    }

//    @Bean
//    public EhCacheManagerFactoryBean ehCacheCacheManager() {
//        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
//       // factory.setConfigLocation(new ClassPathResource("ehcache.xml")); // Đường dẫn đến file cấu hình EhCache
//        factory.setShared(true);
//        return factory;
//    }
}
