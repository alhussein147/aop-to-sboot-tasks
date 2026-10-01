package com.example.lab3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class InventoryAspect {

    @Pointcut("execution(* *..InventoryService.*(..))")
    public void inventoryOps() {
    }

    @Before("inventoryOps()")
    public void before(JoinPoint jp) {
        System.out.println("@Before: " + jp.getSignature().getName()
                + java.util.Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* *..InventoryService.checkStock(..))", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        System.out.println("@AfterReturning: " + jp.getSignature().getName() + " -> " + result);
    }

    @AfterThrowing(pointcut = "execution(* *..InventoryService.reserveStock(..))", throwing = "ex")
    public void afterThrowing(JoinPoint jp, IllegalStateException ex) {
        System.out.println("@AfterThrowing: " + jp.getSignature().getName() + " -> " + ex);
    }

    @After("inventoryOps()")
    public void after(JoinPoint jp) {
        System.out.println("@After (finally): " + jp.getSignature().getName());
    }

    @Around("inventoryOps()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around start: " + pjp.getSignature().getName());
        long t = System.nanoTime();
        try {
            return pjp.proceed();
        } finally {
            System.out.println("@Around end: took " + (System.nanoTime() - t) / 1000 + " µs");
        }
    }
}