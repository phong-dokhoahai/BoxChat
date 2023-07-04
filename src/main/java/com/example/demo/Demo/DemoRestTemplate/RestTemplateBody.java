package com.example.demo.Demo.DemoRestTemplate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class RestTemplateBody {
    private List<Employee> result;
    private String targetUrl;
    private boolean success;
    private String error;
    private boolean unAuthorizedRequest;
    private boolean __abp;
}
