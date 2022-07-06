package com.example;

import org.apache.http.client.methods.HttpRequestWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class ApacheAspect {

    @Autowired private SessionMemoryStorage sessionMemoryStorage;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ApacheAspect.class);
    @Pointcut("execution(public * org.apache.http.impl.execchain.MainClientExec.execute(..))")
    public void customApacheExecutorPointcut() {}

    @Around("customApacheExecutorPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String sessionStorage = sessionMemoryStorage.getSessionStorage();
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof HttpRequestWrapper){
                HttpRequestWrapper arg1 = (HttpRequestWrapper) arg;
                arg1.setHeader("session", sessionStorage);
            }
        }
        log.info("http headers (session: "+ sessionStorage+") added to original request");
        return pjp.proceed();
    }
}