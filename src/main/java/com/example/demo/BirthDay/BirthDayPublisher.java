package com.example.demo.BirthDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class BirthDayPublisher {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    public void birthDayPublish(long id) {
        applicationEventPublisher.publishEvent(new BirthDayEvent(this,id));
    }
}
