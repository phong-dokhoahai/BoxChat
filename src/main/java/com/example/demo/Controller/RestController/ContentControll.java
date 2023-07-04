package com.example.demo.Controller.RestController;

import com.example.demo.Entity.Content;
import com.example.demo.Service.ContentService;
import com.example.demo.Dto.EntityDto.ContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentControll {
    @Autowired
    ContentService contentService;

    @GetMapping("/show-all")
    public ResponseEntity<List<Content>> getContent() {
        List<Content> content = contentService.getAllContent();
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @PatchMapping("/edit")// edit
    public void editContent(@RequestBody ContentDto contentDto) {
        contentService.editContent();
    }

    @DeleteMapping("/delete")// delete
    public void deleteContent(@RequestBody ContentDto contentDto) {
        contentService.deleteContent();
    }
    @GetMapping(value = "/chat-history/{conversationId}")
    public ResponseEntity<List<ContentDto>> messages(@PathVariable(value = "conversationId") long conversationId) {
        System.out.println("content history :"+Thread.currentThread().getName());
        List<ContentDto> contentDtos = contentService.getContentByConversationId(conversationId);
        return ResponseEntity.ok(contentDtos);
    }
}
