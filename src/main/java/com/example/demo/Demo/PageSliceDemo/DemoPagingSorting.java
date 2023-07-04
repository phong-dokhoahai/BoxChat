package com.example.demo.Demo.PageSliceDemo;

import com.example.demo.Demo.DemoJoinTable.ContentSpecifications;
import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Dto.EntityDto.ContentDto;
import com.example.demo.Entity.Content;
import com.example.demo.MapStruct.AccountMaper;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Repository.ContentRepo;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.stream.Collectors;

@RestController
public class DemoPagingSorting {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ContentRepo contentRepo;
    @Autowired
    ModelMapper modelMapper;
    private AccountMaper accountMaper = Mappers.getMapper(AccountMaper.class);
    @GetMapping(value = "/page", params = { "page", "size" })
    public ResponseEntity<Page<AccountDto>>  Paging(@RequestParam("page") int page,
                                                    @RequestParam("size") int size) {

        Sort sortNickName = Sort.by(Sort.Direction.ASC,"nickName");
        Sort sortUsername = Sort.by(Sort.Direction.DESC,"username");
        Sort groupBySort = sortUsername.and(sortNickName);

        Pageable pageable = PageRequest.of(page, size,groupBySort);

        Page<AccountDto> accountDtoPage = accountRepo.findAll(pageable).map(account -> accountMaper.toDto(account));

        System.out.println("total pages:"+accountDtoPage.getTotalPages());
        System.out.println("total elements:"+accountDtoPage.getTotalElements());
        return ResponseEntity.ok(accountDtoPage) ;
    }

    @GetMapping(value = "/slice", params = { "slice", "size" })
    public ResponseEntity<Slice<AccountDto>>  Slice(@RequestParam("slice") int page,
                                                    @RequestParam("size") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Slice<AccountDto> accountDtoSlice = new SliceImpl<>(accountRepo.findAll(pageable).stream().map(account ->
                accountMaper.toDto(account)
        ).collect(Collectors.toList()));

        return ResponseEntity.ok(accountDtoSlice) ;
    }
    @GetMapping("/getContentPageById/{id}")
    public ResponseEntity<Page<ContentDto>> getContentPageById(@PathVariable long id){

        Pageable pageable = PageRequest.of(0 ,10);
        Page<ContentDto> contentDtoPage = contentRepo.findAllById(id,pageable);
        return ResponseEntity.ok(contentDtoPage);
    }
    @GetMapping("/getContentLike")
    public ResponseEntity<Page<ContentDto>> getContentLike(@RequestParam("content")String content){
        Pageable pageable = PageRequest.of(0 ,10);
        Page<Content> contentPage = contentRepo.findAll(ContentSpecifications.hasContentLike(content), pageable);
        Page<ContentDto> contentDtoPage = new PageImpl<>(contentPage
                .stream()
                .map(o -> modelMapper.map(o, ContentDto.class))
                .collect(Collectors.toList())
        );
        return ResponseEntity.ok(contentDtoPage);
    }
}
