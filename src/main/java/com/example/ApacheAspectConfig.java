package com.example;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApacheAspectConfig {

    @Bean
    public ApacheAspect customApacheAspect() {
        ApacheAspect aspect = Aspects.aspectOf(ApacheAspect.class);
        return aspect;
    }

}