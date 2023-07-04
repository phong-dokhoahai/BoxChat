package com.example.demo.MapStruct;

import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Dto.EntityDto.AccountNamePasswordDTO;
import com.example.demo.Dto.EntityDto.ContentDto;
import com.example.demo.Entity.Account;
import com.example.demo.Entity.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface ContentMapper {
    Content toEntity(ContentDto contentDto);
    ContentDto toDto(Content content);
}
