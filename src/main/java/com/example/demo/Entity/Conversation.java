package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String conversationName;

    @Column
    @Enumerated(EnumType.STRING)
    private ConversationType conversationType ;
    public enum ConversationType {
        PERSON, GROUP
    }
    //Relation to Content
    @OneToMany(mappedBy = "conversation", fetch = FetchType.EAGER)
    private List<Content> content;

    //Relation to Account
    @OneToMany(mappedBy = "conversation")
    private List<AccountConversationBroker> accountConversationBroker;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "account_conversation",
//            joinColumns = @JoinColumn(name = "conversation_id"),
//            inverseJoinColumns = @JoinColumn(name = "account_id"))
//    private List<Account> accounts;
}

