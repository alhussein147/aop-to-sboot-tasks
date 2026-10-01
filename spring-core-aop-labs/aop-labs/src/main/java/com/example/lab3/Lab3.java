package com.example.lab3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

public class Lab3 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Lab3Config.class);
        InventoryService s = ctx.getBean(InventoryService.class);

        s.checkStock("A1");
        System.out.println("---");

        s.reserveStock("A1", 5);
        System.out.println("---");

        try { s.reserveStock("A1", 500); }
        catch (IllegalStateException e) { System.out.println("caller: " + e.getMessage()); }
/**
 * custom annotation sample -> enable @cachable in the InventoryServiceImpl
 * **/

//        s.checkStock("A1");
//        s.checkStock("A1");
//        s.checkStock("B2");
//        s.reserveStock("A1", 5);
    }
}