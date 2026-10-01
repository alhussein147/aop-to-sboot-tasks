package com.example.lab2;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

class LogReturn implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object ret, Method m, Object[] args, Object target) {
        if (m.getName().equals("checkStock"))
            System.out.println("AFTER_RETURNING: " + m.getName() + " -> " + ret);
    }
}