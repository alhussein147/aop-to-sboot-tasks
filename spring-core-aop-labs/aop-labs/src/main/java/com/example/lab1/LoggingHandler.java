package com.example.lab1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

class LoggingHandler implements InvocationHandler {
    private final Object target;

    LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("[Proxy] " + method.getName() + Arrays.toString(args));
        long start = System.nanoTime();

        try {

            Object result = method.invoke(target, args);

            System.out.println("[Proxy]" + method.getName() + " returned: " + result);

            return result;

        } catch (InvocationTargetException e) {
            throw e.getCause();
        } finally {
            long micros = (System.nanoTime() - start) / 1000;
            System.out.println(method.getName() + " took " + micros + " µs");
        }
    }
}
