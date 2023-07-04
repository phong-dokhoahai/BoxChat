package com.example.demo.Demo.DemoBeanScope;

import jakarta.annotation.PreDestroy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class ServiceA {
    @Autowired
    private Counter counter;
    public void doSomething() {
        counter.increment();
    }
    @PreDestroy
    public void preDestroyA(){
        System.out.println("Destroy Service A");
    }
}