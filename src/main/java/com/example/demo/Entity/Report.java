package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private ReportType reportType;
    enum ReportType {
        TOXIC
    }
    @Column
    private String nickName;

    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY)
    private List<Content> reportContent;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

}
