package com.example.demo.Service;

import com.example.demo.Dto.EntityDto.ConversationContentDto;
import com.example.demo.Entity.Conversation;
import com.example.demo.Repository.ConversationRepo;
import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Dto.EntityDto.ConversationDto;
import com.example.demo.Security.JwtUtils;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {
    @Autowired
    private ConversationRepo conversationRepo;
    @Autowired
    AccountConversationBrokerService accountConversationBrokerService;
    @Autowired
    JwtUtils jwtUtils;

    // CRUD
    public List<Conversation> getConversation() {
        return conversationRepo.findAll();
    }

    // create a conversation when create account
    public Conversation createConversationForPerson(AccountDto accountDto) {
        Conversation conversation = new Conversation();
        if (accountDto.getNickName().isEmpty()) {
            conversation.setConversationName(accountDto.getNickName());
        } else {
            conversation.setConversationName(accountDto.getFirstName() + accountDto.getLastName());
        }
        conversation.setConversationType(Conversation.ConversationType.PERSON);

        return conversationRepo.save(conversation);
    }

    public void createGroupConversation(String name) {//(ConversationDto conversationDto){
        Conversation conversation = new Conversation();

        conversation.setConversationType(Conversation.ConversationType.GROUP);
        conversation.setConversationName(name);//conversationDto.getConversationName()
        conversationRepo.save(conversation);
    }

    public void addPersonToGroupConversation(AccountDto accountDto, ConversationDto conversationDto) {

        Optional<Conversation> conversationOptional = conversationRepo.findById(conversationDto.getId());
        Conversation conversation = conversationOptional.get();

        if (conversation.getConversationType().equals(Conversation.ConversationType.GROUP)) {
            accountConversationBrokerService.addPersonToGroupConversation(accountDto.getId(), conversation.getId());
        } else System.out.println("add person to group :" + conversation.getConversationName() + " fail!");
    }

    public void deleteConversation(long id) {
        conversationRepo.deleteById(id);
    }

    public void editConversationName(ConversationDto conversationDto) {
        Optional<Conversation> conversationOptional = conversationRepo.findById(conversationDto.getId());
        Conversation conversation = conversationOptional.get();
        conversation.setConversationName(conversationDto.getConversationName());
    }

    public List<ConversationContentDto> findAllContentByConversation() {
        List<ConversationContentDto> conversationContentDtoList = conversationRepo.findAllContentByConversation();
        return conversationContentDtoList;
    }

    public List<ConversationDto> findAllByUsername(String username) {
        return conversationRepo.findConversationByUsername(username);
    }
//    public Conversation createConversationForPerson(Account account) {
//
//        Conversation conversation = new Conversation();
//
//        conversation.setConversationName(account.getNickName());
//        conversation.setConversationType(Conversation.ConversationType.PERSON);
//        conversation.setAccounts(Collections.singletonList(account));
//
//        return conversationRepo.save(conversation);
//    }
}
