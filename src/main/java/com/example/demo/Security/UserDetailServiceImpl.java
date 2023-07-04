package com.example.demo.Security;

import com.example.demo.Entity.Account;

import com.example.demo.Repository.AccountRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername {} (UserDetailServiceImpl_class)", username);

        Account account = accountRepo.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username(UserDetailServiceImpl_class): " + username);
        }
        UserDetails userDetails =
                new User(
                        account.getUsername(),
                        account.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_" + account.getRole()))
                );
        log.info("load authority {}(UserDetailServiceImpl_class)", userDetails.getAuthorities());
        return userDetails;
    }
}