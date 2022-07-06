package com.example;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfig {

    @Bean
    public SessionAspect theAspect() {
        SessionAspect aspect = Aspects.aspectOf(SessionAspect.class);
        return aspect;
    }

}