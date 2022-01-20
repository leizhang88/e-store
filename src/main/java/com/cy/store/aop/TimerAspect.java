package com.cy.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
/**
 * Get statistics of time used by any service class method
 */
public class TimerAspect {
    @Around("execution(* com.cy.store.service..impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Duration: " + (end - start));
        return result;
    }
}
