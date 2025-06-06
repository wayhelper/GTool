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

    @Cacheable(value = "shareCache", key = "#key")
    public SharePram set(int key, SharePram param) {
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
    }
}
