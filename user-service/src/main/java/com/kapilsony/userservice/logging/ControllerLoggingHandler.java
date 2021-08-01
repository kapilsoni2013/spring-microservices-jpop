package com.kapilsony.userservice.logging;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
@Slf4j
public class ControllerLoggingHandler {

    private List<String> ignoredHeader=Arrays
            .asList("user-agent",
                    "accept",
                    "host",
                    "accept-encoding",
                    "connection",
                    "postman-token",
                    "cache-control");

    @EventListener
    public void test(final RefreshScopeRefreshedEvent event){
        System.out.println("RefreshScopeRefreshedEvent: "+event.toString());
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }

    @Pointcut("execution(public * *(..))")
    protected void loggingPublicOperation() {
    }

    @Pointcut("execution(* *.*(..))")
    protected void loggingAllOperation() {
    }

    @Pointcut("within(com.kapilsony.userservice..*)")
    private void logAnyFunctionWithinResource() {
    }

    //before -> Any resource annotated with @Controller annotation
    //and all method and function taking HttpServletRequest as first parameter
    @Before("controller() && allMethod() && args(..,request)")
    public void logBefore(JoinPoint joinPoint, HttpServletRequest request) {

        log.info("AOP: Entering in Method={}, ClassName={}, Arguments={}, TargetClass={}",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringTypeName(),
                Arrays.toString(joinPoint.getArgs()),
                joinPoint.getTarget().getClass().getName());

        if (null != request) {
            log.info("AOP: Start Header Section of __{}__ method type request",
                    request.getMethod());
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                if(!ignoredHeader.contains(headerName.toLowerCase())) {
                    String headerValue = request.getHeader(headerName);
                    log.info("AOP: Header-Name={}, Header-Value={}",headerName ,headerValue);
                }
            }
            log.info("AOP: Request Path info={}",request.getServletPath());
            log.info("AOP: End Header Section of request ");
        }
    }
    //After -> All method within resource annotated with @Controller annotation
    // and return a  value
    @AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        log.info("AOP: Method Return value : " + returnValue);
    }
    //After -> Any method within resource annotated with @Controller annotation
    // throws an exception ...Log it
    @AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("AOP: Exception Occurred={}(), Reason={},\nStackTrace={}",
                joinPoint.getSignature().getName(),
                exception.getMessage(),
                exception.toString());
    }
    //Around -> Any method within resource annotated with @Controller annotation
    @Around("controller() && allMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("AOP: Execution Timing={}.{}():- {} ms", className,methodName,elapsedTime);
            return result;
        } catch (IllegalArgumentException e) {
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