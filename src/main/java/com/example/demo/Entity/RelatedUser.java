package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RelatedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stranger_account_id")
    private Long strangerAccountId;

    @Column(name = "block_account_id")
    private Long blockAccountId;

    @Column(name = "friend_account_id")
    private Long friendAccountId;
    @OneToOne
    private Account account;

//    @Column
//    @Enumerated(EnumType.STRING)
//    private RelatedUserType relatedUserType;
//    enum RelatedUserType {
//        Stranger,Block,Friend
//    }
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "related_user_account",
//            joinColumns = @JoinColumn(name = "related_user_id"),
//            inverseJoinColumns = @JoinColumn(name = "account_id"))
//    private List<Account> users;
}

