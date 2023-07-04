package com.example.demo.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Date dateOfBirth;
    @Column
    private String accountNote;
    @Column
    private String phoneNumber;
    @Column
    private String nickName;
    @Column
    @Nullable
    private boolean gender;
    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    private Role role;

    public enum Role {
        ADMIN, USER
    }

    @OneToOne(mappedBy = "account")
    private RelatedUser relatedUser;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Report> reportList;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<AccountList> accountLists;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<AccountConversationBroker> accountConversationBroker;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Content> contentList;

//    @ManyToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
//    private List<Conversation> conversation;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", accountNote='" + accountNote + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", relatedUser=" + relatedUser +
                ", reportList=" + reportList +
                ", accountConversationBroker=" + accountConversationBroker +
                '}';
    }
}