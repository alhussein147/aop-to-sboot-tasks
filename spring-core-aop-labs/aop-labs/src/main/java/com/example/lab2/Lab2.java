package com.example.lab2;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

public class Lab2 {


    public static void main(String[] args) {
        ProxyFactory pf = new ProxyFactory(new InventoryServiceImpl());
        pf.addAdvice(new LogBefore());
        pf.addAdvice(new TimingInterceptor());
        pf.addAdvice(new LogReturn());
        pf.addAdvice(new LogThrows());;

        ApplicationContext ctx = new AnnotationConfigApplicationContext(Lab2Config.class);
        InventoryService inventoryService = (InventoryService) ctx.getBean("inventoryService");
        inventoryService.checkStock("A1");
        inventoryService.reserveStock("A1", 5);
        try { inventoryService.reserveStock("A1", 500); }
        catch (IllegalStateException e) { System.out.println("caller caught: " + e.getMessage()); }
    }
}