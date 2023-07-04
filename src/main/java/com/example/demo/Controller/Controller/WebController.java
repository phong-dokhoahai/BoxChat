package com.example.demo.Controller.Controller;

import com.example.demo.Repository.AccountRepo;
import com.example.demo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepo accountRepo;
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("/register")
    public String register(){
        return"register";
    }
    @GetMapping("/forgot-password")
    public String forgotPassword(){
        return"forgot-password";
    }

}
