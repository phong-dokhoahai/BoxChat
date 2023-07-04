package com.example.demo.Repository;

import com.example.demo.Dto.EntityDto.ContentDto;
import com.example.demo.Dto.EntityProjection.ContentNicknameProjection;
import com.example.demo.Entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepo extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content> {
    @Query("Select new com.example.demo.Dto.EntityDto.ContentDto(e.id,e.content) From Content e WHERE e.id =:id")
    Page<ContentDto> findAllById(@Param("id")long id, Pageable pageable);

    @Query("Select new com.example.demo.Dto.EntityDto.ContentDto(e.id,e.content,e.account.nickName,e.createdDate) From Content e" +
            " WHERE e.conversation.id =:conversationId")
    List<ContentDto> findAllByConversationId(@Param("conversationId")long conversationId);
}
