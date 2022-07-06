package com.example;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionMemoryStorage {

    private String sessionStorage;

    public String getSessionStorage() {
        return sessionStorage;
    }

    public void setSessionStorage(String sessionStorage) {
        this.sessionStorage = sessionStorage;
    }
}
