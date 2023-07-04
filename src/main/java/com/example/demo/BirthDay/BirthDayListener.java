package com.example.demo.BirthDay;

import com.example.demo.Demo.DemoMail.Mail.MyConstants;
import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Entity.Account;
import com.example.demo.MapStruct.AccountMaper;
import com.example.demo.Repository.AccountRepo;
import jdk.swing.interop.SwingInterOpUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirthDayListener {

    @Autowired
    public JavaMailSender emailSender;
    private AccountMaper accountMaper = Mappers.getMapper(AccountMaper.class);
    @Autowired
    AccountRepo accountRepo;

    @EventListener
    @Async
    public void birthDayListener(BirthDayEvent birthDayEvent) throws InterruptedException {
        Account account = accountRepo.findAccountById(birthDayEvent.getId());
        System.out.println("birthday" +Thread.currentThread().getName());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(account.getEmail());
        message.setSubject("Happy Birthday Email");
        message.setText("Happy Birthday to you!" + account.getFirstName() + " " + account.getLastName());
        // Send Message!
        this.emailSender.send(message);
    }
}

