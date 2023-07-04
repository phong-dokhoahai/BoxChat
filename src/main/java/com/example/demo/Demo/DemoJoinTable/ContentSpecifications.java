package com.example.demo.Demo.DemoJoinTable;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Content;
import com.example.demo.Entity.Conversation;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Repository.ContentRepo;
import jakarta.persistence.criteria.Join;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ContentSpecifications {
    public static Specification<Content> hasConversationId(long conversationId){
        return (root, query, criteriaBuilder) -> {
            Join<Content, Conversation> customerJoin = root.join("conversation");
            return criteriaBuilder.equal(customerJoin.get("id"), conversationId);
        };
    }
    public static Specification<Content> hasContentLike(String content) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("content"), "%" + content + "%");
    }
}
