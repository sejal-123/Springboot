package com.telusko.springdatarestdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER  = LoggerFactory.getLogger(LoggingAspect.class);

//    (return type class_name-method_name(args))
//    * any type or all classes or all methods
//    args = .. --> for any no of parameters
//    This expression is called pointcut


//    JoinPoint is when we want to execute a method call - for ex getJob
//   ==================== Before advice =========================
//    These are all advice
//    Aspect is all those advices in one class
//    Target is the method on which it is getting executed
    @Before("execution(* com.telusko.springdatarestdemo.service.JobService.*(..))")
    public void logMethodCall(JoinPoint jp) {
        LOGGER.info("Method called " + jp.getSignature().getName());
    }

//    For having same method call for 2 seperate methods
    @Before("execution(* com.telusko.springdatarestdemo.service.JobService.getJob(..)) || execution(* com.telusko.springdatarestdemo.service.JobService.updateJob(..))")
    public void logMethodCallTwo(JoinPoint jp) {
        LOGGER.info("Method called 2 " + jp.getSignature().getName());
    }

//    =============== After advice (also called as after finally) ================
    @After("execution(* com.telusko.springdatarestdemo.service.JobService.getJob(..)) || execution(* com.telusko.springdatarestdemo.service.JobService.updateJob(..))")
    public void logMethodCallAfter(JoinPoint jp) {
        LOGGER.info("Method executed " + jp.getSignature().getName());
    }

//    =============== After throwing ================
    @AfterThrowing("execution(* com.telusko.springdatarestdemo.service.JobService.getJob(..)) || execution(* com.telusko.springdatarestdemo.service.JobService.updateJob(..))")
    public void logMethodCallAfterThrowing(JoinPoint jp) {
        LOGGER.info("Method has some issues " + jp.getSignature().getName());
    }

//    =============== After returning ================
    @AfterReturning("execution(* com.telusko.springdatarestdemo.service.JobService.getJob(..)) || execution(* com.telusko.springdatarestdemo.service.JobService.updateJob(..))")
    public void logMethodCallAfterReturning(JoinPoint jp) {
        LOGGER.info("Method executed successfully " + jp.getSignature().getName());
    }


}
