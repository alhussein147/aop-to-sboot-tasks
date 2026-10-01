package com.example.lab2;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class LogThrows implements ThrowsAdvice {

    public void afterThrowing(Method m, Object[] args, Object target, IllegalStateException ex) {
        if (m.getName().equals("reserveStock"))
            System.out.println("AFTER_THROWING: " + m.getName() + " -> " + ex);
    }

}
