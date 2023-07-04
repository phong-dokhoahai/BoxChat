package com.example.demo.Controller.RestController;

import com.example.demo.Dto.EntityDto.ChatMessage;
import com.example.demo.Dto.EntityDto.ContentDto;
import com.example.demo.Dto.EntityDto.ConversationContentDto;
import com.example.demo.Entity.Conversation;
import com.example.demo.Service.ContentService;
import com.example.demo.Service.ConversationService;
import com.example.demo.Dto.EntityDto.ConversationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversation")
public class ConversationControll {
    @Autowired
    ConversationService conversationService;
    @Autowired
    ContentService contentService;

    @GetMapping("/get")
    public ResponseEntity<List<Conversation>> getConversations() {
        List<Conversation> conversation = conversationService.getConversation();
        return new ResponseEntity<>(conversation, HttpStatus.OK);
    }

    @PostMapping("/create-group") // create conversation
    public void createGroupConversation(@RequestBody String name) {//(@RequestBody ConversationDto conversationDto) {
        conversationService.createGroupConversation(name);
    }

    @MessageMapping("/content/send/{conversationId}")
    @SendTo("/conversation/{conversationId}")// create
    public ChatMessage sendContent(@Payload ChatMessage chatMessage, @DestinationVariable("conversationId") long conversationId) {
        System.out.println("id :" + conversationId);
        System.out.println("message received:" + chatMessage.getContent());
        System.out.println(Thread.currentThread().getName());
        try {
           ContentDto contentDto= contentService.sendContent(chatMessage, conversationId);
           chatMessage.setNickName(contentDto.getNickName());
           chatMessage.setCreateDate(contentDto.getCreatedDate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return chatMessage;
    }

    @PatchMapping("/edit-name")// edit conversation
    public void EditConversation(@RequestBody ConversationDto conversationDto) {
        conversationService.editConversationName(conversationDto);
    }

    @DeleteMapping("/delete")// delete conversation
    public void deleteConversation(@RequestBody long id) {
        conversationService.deleteConversation(id);
    }

    @GetMapping("/content")
    public ResponseEntity<List<ConversationContentDto>> findAllContentByConversation() {
        List<ConversationContentDto> conversationContentDtoList = conversationService.findAllContentByConversation();
        return ResponseEntity.ok(conversationContentDtoList);
    }
    @GetMapping("/account/all")
    public List<ConversationDto> messages(@RequestParam("username") String username) {
        List<ConversationDto> conversations = conversationService.findAllByUsername(username);
        conversations.forEach(c -> System.out.println(c.getId() + "-------" + c.getConversationName()));
        return conversations;
    }
}
