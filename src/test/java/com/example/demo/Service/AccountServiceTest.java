package com.example.demo.Service;

import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Entity.Account;
import com.example.demo.Repository.AccountRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;
    @MockBean
    AccountRepo accountRepo;

    @Test
    public void getAccounts_should_pass(){
        List<Account> accountMocks = initAccounts();
        Mockito.when(accountRepo.findAll()).thenReturn(accountMocks);

        List<AccountDto> actualAccounts = accountService.getAccounts();
        Assert.assertEquals(1, actualAccounts.size());
        Assert.assertEquals("phong.dokhoa@ncc.asia", actualAccounts.get(0).getEmail());
    }

    List<Account> initAccounts() {
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        account.setEmail("phong.dokhoa@ncc.asia");
        account.setUsername("phong.dokhoa");
        account.setPassword("phong.dokhoa@ncc.asia");
        account.setGender(true);
        accounts.add(account);
        return accounts;
    }


}
