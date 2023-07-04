package com.example.demo.controller;

import com.example.demo.Controller.RestController.AccountControll;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(AccountControll.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    AccountService accountService;

    @MockBean
    AccountRepo accountRepo;

    @Test
    public void getAccounts_should_pass() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
