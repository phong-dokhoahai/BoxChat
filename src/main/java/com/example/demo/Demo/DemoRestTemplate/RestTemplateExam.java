package com.example.demo.Demo.DemoRestTemplate;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.AccountConversationBroker;
import com.example.demo.Entity.Conversation;
import com.example.demo.Repository.AccountConversationBrokerRepo;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Repository.ConversationRepo;
import com.example.demo.Service.AccountService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RestTemplateExam {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountConversationBrokerRepo accountConversationBrokerRepo;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ConversationRepo conversationRepo;

    public void CreateAccountByRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://64a3dc99c3b509573b56a87c.mockapi.io/createAccount";
//       String apiUrl = "http://hrm-api.nccsoft.vn/api/services/app/CheckIn/GetUserForCheckIn";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            try {
                // Khởi tạo ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                // Phân tích JSON thành một đối tượng JsonNode
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                RestTemplateBody restTemplateBody = new RestTemplateBody();
                List<Employee> employeesList = new ArrayList<>();

                if (jsonNode.isArray()) {
                    for (JsonNode node : jsonNode) {
                        Employee employee = new Employee();

                        employee.setFirstName(node.get("firstName").asText());
                        employee.setLastName(node.get("lastName").asText());
                        employee.setEmail(node.get("email").asText());
                        employee.setPhoneNumber(node.get("phoneNumber").asText());
                        employee.setNickName(node.get("nickName").asText());
                        employee.setGender(node.get("gender").asBoolean());

                        System.out.println(node.get("dateOfBirth"));

                        String dateOfBirthString = node.get("dateOfBirth").asText();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        Date dateOfBirth = dateFormat.parse(dateOfBirthString);
                        employee.setDateOfBirth(dateOfBirth);

                        System.out.println(employee.toString());

                        employeesList.add(employee);
                    }
                }
                restTemplateBody.setResult(employeesList);
//                restTemplateBody.setResult( objectMapper.convertValue(jsonNode.get("result"), new TypeReference<List<Employee>>(){}));
//                restTemplateBody.setError(jsonNode.get("error").asText());
//                restTemplateBody.setSuccess(jsonNode.get("success").asBoolean());
//                restTemplateBody.setUnAuthorizedRequest(jsonNode.get("unAuthorizedRequest").asBoolean());
//                restTemplateBody.set__abp(jsonNode.get("__abp").asBoolean());
//                restTemplateBody.setTargetUrl(jsonNode.get("targetUrl").asText());

                accountService.createAccountByRestTemplate(restTemplateBody);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
@Transactional
    public void MockAccountConversationData() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://64a3dc99c3b509573b56a87c.mockapi.io/MockConversationAccount";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            try {
                // Khởi tạo ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                // Phân tích JSON thành một đối tượng JsonNode
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                if (jsonNode.isArray()) {
                    for (JsonNode node : jsonNode) {
                        AccountConversationBroker accountConversationBroker = new AccountConversationBroker();
                        long accountId= node.get("account_id").asLong();
                        long conversation_id= node.get("conversation_id").asLong();
                        Account account = accountRepo.findAccountById(accountId);
                        Conversation conversation = conversationRepo.findConversationById(conversation_id);

                        accountConversationBroker.setAccount(account);
                        accountConversationBroker.setConversation(conversation);
                        accountConversationBrokerRepo.save(accountConversationBroker);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
