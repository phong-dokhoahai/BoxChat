package com.example.demo.Dto.EntityDto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AccountNamePasswordDTO {
    private String username;
    private String password;
    private String email;
}
