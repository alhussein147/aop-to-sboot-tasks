package com.example.lab2;

import org.aopalliance.intercept.MethodInterceptor;

class TimingInterceptor implements MethodInterceptor {
    public Object invoke(org.aopalliance.intercept.MethodInvocation inv) throws Throwable {
        System.out.println("AROUND start: " + inv.getMethod().getName());
        long t = System.nanoTime();
        try {
            return inv.proceed();
        } finally {
            System.out.println("AROUND finally: took " + (System.nanoTime() - t) / 1000 + " µs");
        }
    }
}