package com.example.demo.Controller.RestController;

import com.example.demo.Demo.DemoRestTemplate.RestTemplateExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockApiController {
    @Autowired
    RestTemplateExam restTemplateExam;

    @GetMapping("/CreateAccountByRestTemplate")
    public void CreateAccountByRestTemplate() {
        restTemplateExam.CreateAccountByRestTemplate();
    }

    @GetMapping("/MockAccountConversationData")
    public void MockAccountConversationData() {
        restTemplateExam.MockAccountConversationData();
    }
}

