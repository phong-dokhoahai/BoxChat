package com.example.demo.Repository;

import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Entity.Account;
import com.example.demo.Entity.AccountConversationBroker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AccountConversationBrokerRepo extends JpaRepository<AccountConversationBroker,Long> {
//    @Query("SELECT * FROM AccountConversationBroker ac INNER JOIN Account a")
//    AccountConversationBroker getContentByAccountNickName();
//    @Query("SELECT ac.account From account_conversation ac WHERE ac.conversation.id= :id")
//    List<Account> findAccountByConversationId(long conversationId);
}
