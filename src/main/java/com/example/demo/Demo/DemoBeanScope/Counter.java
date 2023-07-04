package com.example.demo.Demo.DemoBeanScope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class Counter {
    private int count;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
