package com.example.demo.Demo.DemoRestTemplate;

import com.example.demo.Service.AccountService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class RestTemplateExam {
    @Autowired
    AccountService accountService;
   public void CreateAccountByRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://hrm-api.nccsoft.vn/api/services/app/CheckIn/GetUserForCheckIn";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            try {
                // Khởi tạo ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                // Phân tích JSON thành một đối tượng JsonNode
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                RestTemplateBody restTemplateBody = new RestTemplateBody();

                restTemplateBody.setResult( objectMapper.convertValue(jsonNode.get("result"), new TypeReference<List<Employee>>(){}));
                restTemplateBody.setError(jsonNode.get("error").asText());
                restTemplateBody.setSuccess(jsonNode.get("success").asBoolean());
                restTemplateBody.setUnAuthorizedRequest(jsonNode.get("unAuthorizedRequest").asBoolean());
                restTemplateBody.set__abp(jsonNode.get("__abp").asBoolean());
                restTemplateBody.setTargetUrl(jsonNode.get("targetUrl").asText());

                accountService.createAccountByRestTemplate(restTemplateBody);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
    }
}
