package com.example.demo.Controller.RestController;

import com.example.demo.Dto.EntityDto.AuthenticationDto;
import com.example.demo.Dto.EntityDto.LoginRequest;
import com.example.demo.Entity.Account;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Security.JwtUtils;
import com.example.demo.Service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AuthController {

    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    AccountRepo accountRepo;
    private JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        System.out.println("username :"+loginRequest.getUsername());
        System.out.println("password :"+loginRequest.getPassword());
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        System.out.println("đã đăng nhập");

        final String accessToken = jwtUtils.generateTokenFromUsername(loginRequest.getUsername()) .get("accessToken");
        final String refreshToken = jwtUtils.generateTokenFromUsername(loginRequest.getUsername()) .get("refreshToken");
        Account account = accountRepo.findByUsername(loginRequest.getUsername());
        AuthenticationDto authenticationDto = new AuthenticationDto(account.getId(),loginRequest.getUsername(), accessToken);
        return ResponseEntity.ok( authenticationDto);

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(username,password);
            authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/refreshToken")
    public String createAccessTokenFromRefreshToken(@RequestBody String refreshToken) {
        return jwtUtils.createAccessTokenFromRefreshToken(refreshToken);
    }
}

