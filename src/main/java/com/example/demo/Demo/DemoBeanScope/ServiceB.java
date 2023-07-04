package com.example.demo.Demo.DemoBeanScope;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ServiceB {
    @Autowired
    private Counter counter;

    @Autowired
    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public void doSomething() {
        counter.increment();
    }
}