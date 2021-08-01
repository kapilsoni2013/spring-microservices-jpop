package com.kapilsony.bookservice.logging;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Aspect
@Configuration
@Slf4j
public class LoggingHandler {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Pointcut("within(com.kapilsony.bookservice..*)")
    private void logAnyFunctionWithinResource() {
    }

    @Before("logAnyFunctionWithinResource() && !controller()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("AOP: Entering in Method={}, ClassName={}, Arguments={}, TargetClass={}",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringTypeName(),
                Arrays.toString(joinPoint.getArgs()),
                joinPoint.getTarget().getClass().getName());
    }

    @AfterReturning(pointcut = "logAnyFunctionWithinResource() && !controller()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        log.info("AOP: Method Return value : " + returnValue);
    }

    @AfterThrowing(pointcut = "logAnyFunctionWithinResource() && !controller()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("AOP: Exception Occurred={}(), Reason={},\nStackTrace={}",joinPoint.getSignature().getName(),exception.getMessage(),exception.toString());
    }

    @Around("logAnyFunctionWithinResource() && !controller()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("AOP: Execution Timing={}.{}():- {} ms", className,methodName,elapsedTime);
            return result;
        } catch (Throwable e) {
            log.error("AOP: IllegalArgumentException={} in {}.{}()",
                    Arrays.toString(joinPoint.getArgs()),
                    className,
                    methodName);
            throw e;
        }
    }

    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }
}