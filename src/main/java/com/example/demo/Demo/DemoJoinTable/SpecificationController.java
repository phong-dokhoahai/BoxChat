package com.example.demo.Demo.DemoJoinTable;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Content;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Repository.ContentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.Demo.DemoJoinTable.AccountSpecifications.hasFirstNameLike;
import static com.example.demo.Demo.DemoJoinTable.ContentSpecifications.hasConversationId;

@RestController
public class SpecificationController {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ContentRepo contentRepo;
//    @GetMapping("/test")
//    public void test1() throws InterruptedException {
//        Date dateOfBirth = new Date();
//        DayDto dayDto= new DayDto(dateOfBirth);
//        List<AccountDto> accountList = accountRepo.findAccountDtoByDayOfBirth(dayDto.getDay(),dayDto.getMonth()+1);
//        System.out.println(accountList.size());
//        for (AccountDto a:
//             accountList) {
//            //System.out.println(a.toString());
//        }
//        System.out.println("day : "+dayDto.getDay() +"month :"+dayDto.getMonth());
//    }
    @GetMapping("/testGetLastNameByFistNameLike")
    public void testGetLastNameByFistNameLike(@RequestParam("name")String name){
        Specification<Account> specification = hasFirstNameLike(name);
        List<Account> authors = accountRepo.findAll(specification);
        for (Account account:
                authors) {
            System.out.println(account.getId() + account.getLastName());
        }
    }

    @GetMapping("/testGetContentByConversationId/{id}")
    public void testGetContentByConversationId(@PathVariable long id){
        Specification<Content> spec = hasConversationId(id);
        List<Content> orders = contentRepo.findAll(spec);
        for (Content t:
                orders) {
            System.out.println(t.getId()+t.getContent());
        }
    }
}
