package com.example.lab3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
public class CacheAspect {
    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    @Around("@annotation(com.example.lab3.Cacheable)")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        String key = pjp.getSignature().toShortString() + Arrays.deepToString(pjp.getArgs());
        Object hit = cache.get(key);
        if (hit != null) {
            System.out.println("CACHE HIT: " + key);
            return hit;
        }
        System.out.println("CACHE MISS: " + key);
        Object result = pjp.proceed();
        if (result != null) cache.put(key, result);
        return result;
    }
}