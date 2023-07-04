package com.example.demo.Controller.RestController;

import com.example.demo.Dto.EntityDto.AccountFullNameDto;

import com.example.demo.Entity.AccountList;
import com.example.demo.Service.AccountListService;
import com.example.demo.Service.AccountService;
import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Dto.EntityDto.AccountNamePasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountControll {
//account:http-nio-8080-exec-3
// content history :http-nio-8080-exec-9

    @Autowired
    AccountService accountService;
    @Autowired
    AccountListService accountListService;

    @GetMapping// get all account
    public ResponseEntity<List<AccountDto>> getAccounts() {
        System.out.println("account:" + Thread.currentThread().getName());
        List<AccountDto> accountDtos = accountService.getAccounts();
        return new ResponseEntity<>(accountDtos, HttpStatus.OK);
    }

    @GetMapping("/projection") // get all account
    public List<AccountFullNameDto> getAccountProjection(Date date) {
        List<AccountFullNameDto> accounts = accountService.getAccountsFullName(date);
        return accounts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable long id) {
        AccountDto accountDto = accountService.getAnAccountById(id);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok(accountDto);
    }

//    @GetMapping("/note/{note}")
//    ResponseEntity<List<Account>> getAccountByNote(@PathVariable String note) {
//        List<Account> account = accountRepo.findAccountByAccountNote(note);
//        System.out.println(account);
//        return ResponseEntity.ok(account);
//    }
//
//    @GetMapping("/role")
//    ResponseEntity<List<Account>> getAccountByRole(@RequestParam String role) {
//        List<Account> account = accountRepo.findAccountByRole(Account.Role.valueOf(role));
//        return ResponseEntity.ok(account);
//    }

    @PostMapping(
            value = "/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void createAccount(@RequestBody AccountNamePasswordDTO accountNamePasswordDTO) {
        accountService.createAccount(accountNamePasswordDTO);
    }

    @PatchMapping("/information/edit")// edit account
    public void EditAccount(@RequestBody AccountDto accountDto) {
        System.out.println("đã nhận yêu cầu sửa đổi thông tin tài khoản");
        accountService.editAccount(accountDto);
    }

    @DeleteMapping("/admin/{id}")// delete account
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccountById(id);
    }

    @PatchMapping("/{id}/admin")// check admin
    public void isAdmin(@PathVariable long id) {
        accountService.updateAccountToAdmin(id);
    }

    //    add related
    @PostMapping("/friend/add")
    public ResponseEntity<String> addAccountAsFriend(@RequestParam long accountSourceId, @RequestParam long accountDestinationId) {
        accountService.addRelatedAccount(accountSourceId, accountDestinationId, AccountList.Type.FRIEND);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/block/add")
    public ResponseEntity<String> addAccountAsBlock(@RequestParam long accountSourceId, @RequestParam long accountDestinationId) {
        accountService.addRelatedAccount(accountSourceId, accountDestinationId, AccountList.Type.BLOCK);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/stranger/add")
    public ResponseEntity<String> addAccountAsStranger(@RequestParam long accountSourceId, @RequestParam long accountDestinationId) {
        accountService.addRelatedAccount(accountSourceId, accountDestinationId, AccountList.Type.STRANGER);
        return ResponseEntity.ok("OK");
    }

    //     remove related
    @DeleteMapping("/friend/delete")// delete account
    public void removeFriend(@RequestParam long accountSourceId, @RequestParam long accountDestinationId) {
        accountService.removeAccountFromList(accountSourceId, accountDestinationId, AccountList.Type.FRIEND);
    }

    @DeleteMapping("/block/delete")// delete account
    public void removeBlock(@RequestParam long accountSourceId, @RequestParam long accountDestinationId) {
        accountService.removeAccountFromList(accountSourceId, accountDestinationId, AccountList.Type.FRIEND);
    }

    @DeleteMapping("/stranger/delete")// delete account
    public void removeStranger(@RequestParam long accountSourceId, @RequestParam long accountDestinationId) {
        accountService.removeAccountFromList(accountSourceId, accountDestinationId, AccountList.Type.FRIEND);
    }
}
