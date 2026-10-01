package com.example.lab3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class Lab3Config {
    @Bean
    public InventoryService inventoryService() {
        return new InventoryServiceImpl();
    }

    @Bean
    public InventoryAspect inventoryAspect() {
        return new InventoryAspect();
    }

    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect();
    }
}
