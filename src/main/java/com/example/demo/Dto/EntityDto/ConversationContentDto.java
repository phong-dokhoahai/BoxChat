package com.example.demo.Dto.EntityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ConversationContentDto {
    private long conversationId;
    private String message;

    public ConversationContentDto(long conversationId, String message) {
        this.conversationId = conversationId;
        this.message = message;
    }
}
