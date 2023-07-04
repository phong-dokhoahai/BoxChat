package com.example.demo.Dto.EntityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String username;
    private String content;
    private String token;
    private String nickName;
    private Date createDate;
}
