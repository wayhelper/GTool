package com.jason.gtool.common.utils;

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
    /**
     * solve same class not use cache problem
     * @return
     */
    ShareCache getProxy() {
        return this.applicationContext.getBean(ShareCache.class);
    }

    private static ConcurrentHashMap<Integer, Long> cache = new ConcurrentHashMap<>();
    @Cacheable(value = "shareCache", key = "#key")
    public SharePram set(int key, SharePram param) {
        cache.put(key, this.getTimeStampAfter5Minutes());
        return param;
    }
    @Cacheable(value = "shareCache", key = "#key", unless = "#result == null")
    public SharePram get(int key) {
        //TODO
        return null;
    }
    @CacheEvict(value = "shareCache", key = "#key")
    public void del(int key) {
        //TODO
        System.out.println("del +++"+ key);
        cache.remove(key);
    }

    /**
     * Clear expired cache. The error is within 3 seconds.
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
     * @return return timestamp after 5 minutes
     */
    private long getTimeStampAfter5Minutes() {
        return System.currentTimeMillis()+ 5 * 60 * 1000;
    }
}
