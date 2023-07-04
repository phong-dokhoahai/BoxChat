package com.example.demo.Demo.DemoRestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    RestTemplateExam restTemplateExam;
    @GetMapping("/CreateAccountByRestTemplate")
    public void CreateAccountByRestTemplate(){
        restTemplateExam.CreateAccountByRestTemplate();
    }
}
