package com.example.demo.Demo.DemoBeanScope;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Scope("request")
public class CounterController {
    ServiceA serviceA;
    ServiceB serviceB;

    public CounterController(ServiceA serviceA, ServiceB serviceB) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
    }
    @PostMapping("/counterAB")
    public void postSomethingAB(){
        serviceA.doSomething();
        serviceB.doSomething();
        System.out.println("post countA: "+serviceA.getCounter().getCount());
        System.out.println("post countB: "+serviceB.getCounter().getCount());
    }
    @GetMapping("/counterAB")
    public void getSomethingAB(){
        serviceA.doSomething();
        serviceB.doSomething();
        System.out.println("get countA: "+serviceA.getCounter().getCount());
        System.out.println("get countB: "+serviceB.getCounter().getCount());
    }
}
