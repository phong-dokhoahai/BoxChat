package com.example.demo.Repository;

import com.example.demo.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReportRepo  extends JpaRepository<Report, Long> {
}
