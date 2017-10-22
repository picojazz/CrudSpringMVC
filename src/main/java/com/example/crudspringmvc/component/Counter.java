package com.example.crudspringmvc.component;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Counter {
    Integer i;

    public Counter() {
        this.i = 0;
    }
    public void count(){
        this.i++;
    }

    public Integer getI() {
        return i;
    }
}
