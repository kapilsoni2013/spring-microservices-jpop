package com.kapilsony.libraryservice.logging;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LoggingHandler {

    private List<String> ignoredHeader=Arrays
            .asList("user-agent",
                    "accept",
                    "host",
                    "accept-encoding",
                    "connection",
                    "postman-token",
                    "cache-control");

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

    @Pointcut("within(com.kapilsony.libraryservice..*)")
    private void logAnyFunctionWithinResource() {
    }

    //before -> Any resource annotated with @Controller annotation
    //and all method and function taking HttpServletRequest as first parameter
    @Before("controller() && allMethod() && args(..,request)")
    public void logBefore(JoinPoint joinPoint, HttpServletRequest request) {

        log.info("AOP: Entering in Method :  " + joinPoint.getSignature().getName());
        log.info("AOP: Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("AOP: Arguments :  " + Arrays.toString(joinPoint.getArgs()));
        log.info("AOP: Target class : " + joinPoint.getTarget().getClass().getName());

        if (null != request) {
            log.info("AOP: Start Header Section of request ");
            log.info("AOP: Method Type : " + request.getMethod());
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                if(!ignoredHeader.contains(headerName.toLowerCase())) {
                    String headerValue = request.getHeader(headerName);
                    log.info("AOP: Header Name: " + headerName + ", Header Value : " + headerValue);
                }
            }
            log.info("AOP: Request Path info :" + request.getServletPath());
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
        log.error("AOP: An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        log.error("AOP: Cause : " + exception.getMessage());
    }
    //Around -> Any method within resource annotated with @Controller annotation
    @Around("controller() && allMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("AOP: Method Reponse Timing: " + className + "." + methodName + " ()" + " execution time : "
                    + elapsedTime + " ms");

            return result;
        } catch (IllegalArgumentException e) {
            log.error("AOP: Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
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