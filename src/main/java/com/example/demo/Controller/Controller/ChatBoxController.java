package com.example.demo.Controller.Controller;

import com.example.demo.Dto.EntityDto.ContentDto;
import com.example.demo.Dto.EntityDto.ConversationDto;
import com.example.demo.Entity.Conversation;
import com.example.demo.Service.ContentService;
import com.example.demo.Service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ChatBoxController {

    @Autowired
    ConversationService conversationService;
    @Autowired
    ContentService contentService;

    @GetMapping(value = "/chatbox")
    public String messages(@RequestParam(value = "username") String username, Model model) {
        List<ConversationDto> conversations = conversationService.findAllByUsername(username);
        conversations.forEach(c -> System.out.println(c.getId() + "-------" + c.getConversationName()));
        model.addAttribute("conversations", conversations);
        model.addAttribute("demo", "hello world");
        return "chatbox";
    }
    @GetMapping(value = "/chatbox/{conversationId}")
    public ResponseEntity<List<ContentDto>> messages(@PathVariable(value = "conversationId") long conversationId) {
        System.out.println("has call");
        List<ContentDto> contentDtos = contentService.getContentByConversationId(conversationId);
        return ResponseEntity.ok(contentDtos);
    }
}
