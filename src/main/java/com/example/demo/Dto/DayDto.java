package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
@AllArgsConstructor
public class DayDto {
    private Date date;
    private int day;
    private int month;

    public DayDto(Date date) {
        this.day = date.getDate();
        this.month = date.getMonth();
    }
}
