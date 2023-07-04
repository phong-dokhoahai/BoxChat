package com.example.demo.Controller.RestController;

import com.example.demo.Entity.Report;
import com.example.demo.Service.ReportService;
import com.example.demo.Dto.EntityDto.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    public class ReportControll {
        @Autowired
        ReportService reportService;
        @GetMapping("/report")
        public ResponseEntity<List<Report>> getReport() {

            List<Report> report = reportService.getReport();
            return new ResponseEntity<>(report, HttpStatus.OK);
        }
        @PostMapping("/report") // create
        public void createGroupReport(@RequestBody ReportDto ReportDto) {
            reportService.createReport();
        }

        @PatchMapping("/report")// edit
        public void EditAccount(@RequestBody ReportDto ReportDto) {
            reportService.editReport();
        }

        @DeleteMapping("/report")// delete
        public void deleteAccount(@RequestBody ReportDto ReportDto) {
            reportService.deleteReport();
        }

    }

