package com.example.demo.Demo.DemoRestTemplate;

import com.example.demo.Entity.Account;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String accountNote;
    private String phoneNumber;
    private String nickName;
    private boolean gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    private Account.Role role;

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", accountNote='" + accountNote + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", role=" + role +
                '}';
    }
}
