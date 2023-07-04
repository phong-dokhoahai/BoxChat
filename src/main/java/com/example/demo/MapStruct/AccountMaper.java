package com.example.demo.MapStruct;

import com.example.demo.Entity.Account;
import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Dto.EntityDto.AccountNamePasswordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface AccountMaper {
    Account toEntity(AccountDto accountDto);
    @Mapping(source = "username", target = "username")
    Account toEntity(AccountNamePasswordDTO accountNamePasswordDTO);
    AccountDto toDto(Account account);

}
