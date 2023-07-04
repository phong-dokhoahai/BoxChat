package com.example.demo.Service;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.AccountList;
import com.example.demo.Entity.RelatedUser;
import com.example.demo.Repository.AccountListRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountListService {
    @Autowired
    AccountListRepo accountListRepo;

    public List<AccountList> getAllListOfAccount() {
        return accountListRepo.findAll();
    }

    public void createThreeListOfAccount(Account account) {
        AccountList strangerList = new AccountList();
        AccountList friendList = new AccountList();
        AccountList blockList = new AccountList();

        strangerList.setTypeList(AccountList.Type.STRANGER);
        friendList.setTypeList(AccountList.Type.FRIEND);
        blockList.setTypeList(AccountList.Type.BLOCK);

        strangerList.setAccount(account);
        friendList.setAccount(account);
        blockList.setAccount(account);

        accountListRepo.save(strangerList);
        accountListRepo.save(friendList);
        accountListRepo.save(blockList);

    }

}
