package com.example.lab2;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogBefore implements MethodBeforeAdvice {

    @Override
    public void before(Method m, Object[] args, Object target) {
        System.out.println("BEFORE: " + m.getName() + Arrays.toString(args));
    }
}