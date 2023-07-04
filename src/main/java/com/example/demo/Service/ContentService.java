package com.example.demo.Service;

import com.example.demo.Dto.EntityDto.ChatMessage;
import com.example.demo.Dto.EntityDto.ContentDto;
import com.example.demo.MapStruct.AccountMaper;
import com.example.demo.MapStruct.ContentMapper;
import com.example.demo.Repository.ConversationRepo;
import com.example.demo.Security.JwtUtils;
import com.example.demo.Entity.Account;
import com.example.demo.Entity.Content;
import com.example.demo.Entity.Conversation;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Repository.ContentRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Demo.DemoJoinTable.ContentSpecifications.hasConversationId;

@Service
public class ContentService {
    @Autowired
    ContentRepo contentRepo;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ConversationRepo conversationRepo;
    @Autowired
    JwtUtils jwtUtils;
    private ContentMapper contentMapper = Mappers.getMapper(ContentMapper.class);

    // CREATE
    public void sendContent(String message,
                            String token,
                            long conversationId) {

        Content content = new Content();
        content.setContentType(Content.ContentType.MESSAGE);
        content.setCreatedDate(new Date());
        content.setContent(message);

        String username = jwtUtils.getUserNameFromJwtToken(token);
        Account account = accountRepo.findByUsername(username);

        Conversation conversation = conversationRepo.findConversationById(conversationId);
        content.setConversation(conversation);
        contentRepo.save(content);
    }
    public ContentDto sendContent(ChatMessage chatMessage,
                            long conversationId ) {
        Content content = new Content();
        content.setContentType(Content.ContentType.MESSAGE);
        content.setCreatedDate(new Date());
        content.setContent(chatMessage.getContent());

        Account account = accountRepo.findByUsername(jwtUtils.getUserNameFromJwtToken(chatMessage.getToken()));
        content.setAccount(account);
        Conversation conversation = conversationRepo.findConversationById(conversationId);
        content.setConversation(conversation);
        contentRepo.save(content);
        ContentDto contentDto= contentMapper.toDto(content);
        contentDto.setNickName(content.getAccount().getNickName());
        return contentDto;
    }

    // READ
    public List<Content> getAllContent() {
        return contentRepo.findAll();
    }

    // UPDATE
    public void editContent() {

    }
    //DELETE
    public void deleteContent() {

    }
    public List<ContentDto> getContentByConversationId(long conversationId) {
        ArrayList<ContentDto> contentDtoList= (ArrayList<ContentDto>) contentRepo.findAllByConversationId(conversationId);
        for (ContentDto e:
                contentDtoList) {
            System.out.println("nickname "+e.getNickName());
            System.out.println("content  "+e.getContent());
            System.out.println("create date:  "+e.getCreatedDate());
        }
        return contentDtoList;
    }
}
