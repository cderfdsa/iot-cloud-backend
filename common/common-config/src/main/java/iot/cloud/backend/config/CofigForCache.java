package iot.cloud.backend.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author weichuang
 */
@Configuration
public class CofigForCache {

    @Bean(name = "cacheManagerForLoginOrRegister")
    public CacheManager oneHourCacheManager() {
        Caffeine caffeine = Caffeine.newBuilder()
                .initialCapacity(10) //初始大小
                .maximumSize(100)  //最大大小
                .expireAfterWrite(30, TimeUnit.MINUTES);

        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setAllowNullValues(true);
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
