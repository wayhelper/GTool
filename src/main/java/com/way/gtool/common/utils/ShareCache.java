package com.way.gtool.common.utils;

import com.way.gtool.domain.req.SharePram;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

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
