package com.example.demo.Dto.EntityDto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AccountConversationBrokerDto {
    private long id;
    private long accountId;
    private long conversationId;
}
