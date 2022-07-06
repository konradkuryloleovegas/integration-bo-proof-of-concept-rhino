package com.example;

import org.apache.http.client.methods.HttpRequestWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ApacheAspect {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ApacheAspect.class);
    @Pointcut("execution(public * org.apache.http.impl.execchain.MainClientExec.execute(..))")
    public void callAt() {
    }

    @Around("callAt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof HttpRequestWrapper){
                HttpRequestWrapper arg1 = (HttpRequestWrapper) arg;
                arg1.setHeader("session", new SpringAopTracker().getSessionStorage());
            }
        }
        log.info("session added to http headers");
        return pjp.proceed();
    }
}