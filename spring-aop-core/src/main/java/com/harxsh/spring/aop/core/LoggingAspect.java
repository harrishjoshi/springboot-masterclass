package com.harxsh.spring.aop.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.harxsh.spring.aop.core.Homework.checkHomework(..))")
    public void beforeLogger(JoinPoint joinPoint) {
        //System.out.println(joinPoint.getSignature());
        String arg = joinPoint.getArgs()[0].toString();
        System.out.println("Logging aspect's beforeLogger method is called with Arg: " + arg);
    }

    @After("execution(* *checkHomework(..))")
    public void afterLogger() {
        System.out.println("Logging aspect's afterLogger method is called.");
    }

    @Pointcut("execution(* *checkedDateAndTime())")
    public void afterReturningPointCut() {

    }

    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "returnValue")
    public void afterReturningLogger(Date returnValue) {
        System.out.println("Logging aspect's afterReturningLogger method is called with date: " + returnValue);
    }
}
