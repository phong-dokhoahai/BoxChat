package com.example.demo.Controller.RestController;

import com.example.demo.Entity.AccountConversationBroker;
import com.example.demo.Service.AccountConversationBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountConversationBrokerController {
    @Autowired
    AccountConversationBrokerService accountConversationBrokerService;
//    @GetMapping("/getall")
//    public ResponseEntity<AccountConversationBroker> getAllWithAccount(){
//      return   ResponseEntity.ok(accountConversationBrokerService.getAllWithAccount());
//    }
}
