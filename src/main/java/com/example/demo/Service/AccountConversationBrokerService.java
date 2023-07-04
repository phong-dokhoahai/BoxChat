package com.example.demo.Service;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.AccountConversationBroker;
import com.example.demo.Entity.Conversation;
import com.example.demo.Repository.AccountConversationBrokerRepo;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Repository.ConversationRepo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountConversationBrokerService {
    AccountConversationBrokerRepo accountConversationBrokerRepo;
    ConversationRepo conversationRepo;
    AccountRepo accountRepo;

    public AccountConversationBrokerService(AccountConversationBrokerRepo accountConversationBrokerRepo, ConversationRepo conversationRepo, AccountRepo accountRepo) {
        this.accountConversationBrokerRepo = accountConversationBrokerRepo;
        this.conversationRepo = conversationRepo;
        this.accountRepo = accountRepo;
    }

    public void create(Account account, Conversation conversation) {
        AccountConversationBroker accountConversationBroker = new AccountConversationBroker();
        accountConversationBroker.setAccount(account);
        accountConversationBroker.setConversation(conversation);

        accountConversationBrokerRepo.save(accountConversationBroker);
    }

    public void addPersonToGroupConversation(long accountId, long conversationId) {
        AccountConversationBroker accountConversationBroker = new AccountConversationBroker();
        Optional<Account> accountOptional = accountRepo.findById(accountId);
        Account account = accountOptional.get();
        Optional<Conversation> conversationOptional = conversationRepo.findById(conversationId);
        Conversation conversation = conversationOptional.get();

        accountConversationBroker.setAccount(account);
        accountConversationBroker.setConversation(conversation);
    }
//    public AccountConversationBroker getAllWithAccount(){
//       return accountConversationBrokerRepo.getAccountIdConversationId();
//    }
}
