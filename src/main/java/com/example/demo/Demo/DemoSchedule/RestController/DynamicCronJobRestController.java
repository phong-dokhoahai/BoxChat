package com.example.demo.Demo.DemoSchedule.RestController;

import com.example.demo.Demo.DemoSchedule.CronObj;
import com.example.demo.Demo.DemoSchedule.Service.CronJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicCronJobRestController {
    @Autowired
    private CronJobService cronJobService;

      @PostMapping("/dynamic-cronjob/update-cron")
    public void postCron(@RequestBody CronObj cronObj ) throws SchedulerException {
          cronJobService.updateScheduleJob(cronObj.getCronExpression());
      }
}
