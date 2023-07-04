package com.example.demo.Demo.DemoFileConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {
    @Autowired
    Test1 test1;
    @GetMapping("/config/getvalue")
    public void getvalue(){
        System.out.println("name :"+ test1.name);
        System.out.println("version :"+ test1.version);
    }
}
