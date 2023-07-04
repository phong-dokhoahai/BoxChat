package com.example.demo.Repository;

import com.example.demo.Dto.EntityDto.ConversationContentDto;
import com.example.demo.Dto.EntityDto.ConversationDto;
import com.example.demo.Entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationRepo extends JpaRepository<Conversation, Long>, JpaSpecificationExecutor<Conversation> {
    Conversation findByConversationName(String name);

    @Query("Select e From Conversation e Where e.id = ?1")
    Conversation findConversationById(long id);

    @Query("SELECT new com.example.demo.Dto.EntityDto.ConversationContentDto(d.id, e.content) "
            + "FROM Conversation d INNER JOIN d.content e")
    List<ConversationContentDto> findAllContentByConversation();

    @Query(value = "Select new com.example.demo.Dto.EntityDto.ConversationDto(c.id, c.conversationName) From Conversation c " +
            "JOIN account_conversation acb ON acb.conversation.id = c.id " +
            "JOIN Account a ON a.id = acb.account.id " +
            "WHERE a.username = :username")
    List<ConversationDto> findConversationByUsername(@Param(value = "username") String username);
}
