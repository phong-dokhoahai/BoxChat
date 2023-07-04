package com.example.demo.Demo.DemoMail.MailController;


import com.example.demo.Demo.DemoMail.Mail.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleEmailExampleController {

    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @PostMapping("/sendSimpleEmail")
    public String sendSimpleEmail() {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");
        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }

}