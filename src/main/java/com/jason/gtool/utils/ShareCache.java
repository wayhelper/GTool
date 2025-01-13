package com.jason.gtool.utils;

import com.jason.gtool.domain.req.SharePram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ShareCache {
    @Autowired
    ApplicationContext applicationContext;

    //在同一个类中调用方法，导致缓存不生效的问题及解决办法
    ShareCache getProxy() {
        return this.applicationContext.getBean(ShareCache.class);
    }

    private static ConcurrentHashMap<String, Long> cache = new ConcurrentHashMap<>();
    @Cacheable(value = "shareCache", key = "#key")
    public SharePram set(String key, SharePram param) {
        cache.put(key, this.getTimeStampAfter5Minutes());
        return param;
    }
    @Cacheable(value = "shareCache", key = "#key", unless = "#result == null")
    public SharePram get(String key) {
        //TODO
        return null;
    }
    @CacheEvict(value = "shareCache", key = "#key")
    public void del(String key) {
        //TODO
        System.out.println("del +++"+ key);
        cache.remove(key);
    }

    /**
     * 清除过期缓存
     */
    @Scheduled(cron = "0/3 * * * * ?") //3秒执行一次
    public void schdule () {
        long now = System.currentTimeMillis();
        cache.forEach((key, value) -> {
            if (now > value) {
                this.getProxy().del(key);
            }
        });
    }

    /**
     * @return 5分钟后的时间戳
     */
    private long getTimeStampAfter5Minutes() {
        return System.currentTimeMillis()+ 5 * 60 * 1000;
    }
}
