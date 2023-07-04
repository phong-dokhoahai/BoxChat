package com.example.demo.Demo.DemoSchedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class MyJob implements Job {

    public void writeCurrentTime() {
        Date now = new Date();
        System.out.println("Scheduling running now is: "+ now);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        writeCurrentTime();
        System.out.println(Thread.currentThread().getName());
    }
}
