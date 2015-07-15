package com.lundgbg.skrofs.dao;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoPerformanceLogger {

    @Around("execution(public * com.lundgbg.skrofs.dao.*Dao.*(..))")
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {

        final long startTime = System.currentTimeMillis();
        final Object retVal = joinPoint.proceed();
        final long totalTime = System.currentTimeMillis() - startTime;

        System.out.println(String.format("%s.%s: %dms", joinPoint.getSignature().getDeclaringType().getSimpleName(),
                        joinPoint.getSignature().getName(), totalTime));

        return retVal;
    }
}
