package com.telusko.springdatarestdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationtAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationtAspect.class);

    @Around("execution(* com.telusko.springdatarestdemo.service.JobService.getJob(..)) && args(postId) || execution(* com.telusko.springdatarestdemo.service.JobService.updateJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint jp, int postId) throws Throwable {
        if (postId < 0) {
            LOGGER.info(postId + " postId is negative, update it to " + (-postId));
            postId = -postId;
        }
        Object obj = jp.proceed(new Object[]{postId});
        return obj;
    }

}
