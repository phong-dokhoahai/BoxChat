package com.example.demo.Service;

import com.example.demo.Entity.Report;
import com.example.demo.Repository.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepo reportRepo;

    public void createReport() {
    }
    public List<Report> getReport() {
        return reportRepo.findAll();
    }

    public void editReport() {
    }
    public void deleteReport() {
    }
}
