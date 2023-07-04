package com.example.demo.Dto.EntityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AccountFullNameDto {
    private String fullName;
    private String email;
}
