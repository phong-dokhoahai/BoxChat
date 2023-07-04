package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date createdDate;
    @Column
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    public enum ContentType {
        MESSAGE,VIDEO,IMAGE,FILE
    }

    @Column
    private String content;

    // Relation to Conversation Table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="conversation_id")
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    //Relation to Report Table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

}
