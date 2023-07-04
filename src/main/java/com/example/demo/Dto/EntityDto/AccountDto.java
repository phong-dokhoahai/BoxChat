package com.example.demo.Dto.EntityDto;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String accountNote;
    private String phoneNumber;
    private String nickName;
    private boolean gender;
    private String email;

    public AccountDto(long id ,String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", accountNote='" + accountNote + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
}
