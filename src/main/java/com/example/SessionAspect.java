package com.example;

import org.apache.http.client.methods.HttpRequestWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Aspect
public class SessionAspect {

    @Autowired
    private SessionMemoryStorage sessionMemoryStorage;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SessionAspect.class);

    @Pointcut("execution(public * com.example.SpringAopTracker.getSessionStorage(..))")
    public void callAt() {
    }

    @Around("callAt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("ultra aspected");
        pjp.proceed();
        return sessionMemoryStorage.getSessionStorage();
    }
}