package com.example.demo.Demo.DemoSchedule.Service;

import com.example.demo.Demo.DemoSchedule.MyJob;
import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class CronJobService {
    @Autowired
    private  SchedulerFactoryBean schedulerFactoryBean;
    private String cronExpression = "*/5 0 * * * ?";
    @PostConstruct
    public void scheduleJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "myGroup")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "myGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
            scheduler.deleteJob(jobDetail.getKey());
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
    }
    public void updateScheduleJob(String newCronExpression) throws SchedulerException {
        this.cronExpression=newCronExpression;
        scheduleJob();
    }
}
