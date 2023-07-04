package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "AccountList")
@Getter
@Setter
public class AccountList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<Long> accountIdList;

    @Enumerated(EnumType.STRING)
    private Type typeList;
    public enum Type {
        STRANGER, FRIEND,BLOCK
    }
    @ManyToOne
    private Account account;
}
