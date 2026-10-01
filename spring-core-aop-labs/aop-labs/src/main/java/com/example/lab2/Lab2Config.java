package com.example.lab2;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public  class Lab2Config {

    @Bean(autowireCandidate = false)
    InventoryServiceImpl inventoryTarget() {
        return new InventoryServiceImpl();
    }

    @Bean
    MethodBeforeAdvice logBefore() {
        return new LogBefore();
    }

    @Bean
    MethodInterceptor timing() {
        return new TimingInterceptor();
    }

    @Bean
    AfterReturningAdvice logReturn() {
        return new LogReturn();
    }

    @Bean
    ThrowsAdvice logThrows() {
        return new LogThrows();
    }

    @Bean
    ProxyFactoryBean inventoryService() throws ClassNotFoundException {

        ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.setTarget(inventoryTarget());
        pfb.setProxyInterfaces(new Class<?>[]{InventoryService.class});
        pfb.setInterceptorNames("logBefore", "timing", "logReturn", "logThrows");
        return pfb;
    }
}