package com.thoughtworks.grad.concretepage.aspect;

import com.thoughtworks.grad.concretepage.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class TimeLoggingAspect {
    @Before("execution(* com.thoughtworks.grad.concretepage.service.*.*(..))")
    public void logBefore() {
        Logger.log("@Before:");
    }

    @After("execution(* com.thoughtworks.grad.concretepage.service.*.*(..))")
    public void logAfter() {
        System.out.println("@After:" + new Date());
    }

    @Around("execution(* com.thoughtworks.grad.concretepage.service.*.*(..))")
    public void userAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("@Around: Before calculation-" + new Date());
        joinPoint.proceed();
        System.out.println("@Around: After calculation-" + new Date());
    }

    @AfterReturning(pointcut = "execution(* com.thoughtworks.grad.concretepage.service.*.*(..))", returning = "obj")
    public void logAfterReturning(Object obj) {
        System.out.println("Method return value:" + obj);
        System.out.println("@AfterReturning:" + new Date());
    }

    @AfterThrowing(pointcut = "execution(* com.thoughtworks.grad.concretepage.service.*.*(..))", throwing = "e")
    public void logAfterThrowing(Exception e) {
        System.out.println("@AfterReturning:" + new Date());
        System.out.println("Exception caught:" + e.getMessage());
    }
}
