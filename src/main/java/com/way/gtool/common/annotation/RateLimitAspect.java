package com.way.gtool.common.annotation;

import com.way.gtool.common.error.GToolException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class RateLimitAspect {

    // key: method + user/ip，value: 调用计数器和起始时间
    private final Map<String, Counter> counterMap = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = joinPoint.getSignature().toShortString();
        Counter counter = counterMap.computeIfAbsent(key, k -> new Counter());

        long now = System.currentTimeMillis();
        synchronized (counter) {
            if (now - counter.startTime > rateLimit.time() * 1000L) {
                counter.startTime = now;
                counter.count.set(0);
            }

            if (counter.count.incrementAndGet() > rateLimit.limit()) {
                throw new GToolException("访问太多了，请稍后再试！");
            }
        }

        return joinPoint.proceed();
    }

    static class Counter {
        AtomicInteger count = new AtomicInteger(0);
        long startTime = System.currentTimeMillis();
    }
}
