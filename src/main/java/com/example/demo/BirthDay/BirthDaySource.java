package com.example.demo.BirthDay;

import com.example.demo.Dto.DayDto;
import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Dto.EntityDto.AccountFullNameDto;
import com.example.demo.Entity.Account;
import com.example.demo.Repository.AccountRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class BirthDaySource {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    BirthDayPublisher birthDayPublisher;
    @Autowired
    BirthDayListener birthDayListener;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkBirthDayAllAccount() throws InterruptedException {
        Date dateOfBirth = new Date();
        DayDto dayDto = new DayDto(dateOfBirth);
        List<AccountDto> accountList = accountRepo.findAccountDtoByDayOfBirth(dayDto.getDay(), dayDto.getMonth() + 1);

        if (CollectionUtils.isEmpty(accountList)) {
            System.out.println("birthdaylist is empty !");
            return;
        }
        for (AccountDto accountDto : accountList) {
            birthDayPublisher.birthDayPublish(accountDto.getId());
        }
    }

}
