//package com.example.demo.Schedule;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WriteCurrentTimeSchedule {
//int i=0;
//    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");
//
//    // initialDelay = 3 second.
//    // fixedDelay = 2 second.
//    //@Scheduled( initialDelay = 3 * 1000, fixedDelay = 2 * 1000)
//    @Scheduled(cron = "*/5 * * * * *")
//    public void writeCurrentTime() {
//        Date now = new Date();
//        String nowString = df.format(now);
//        System.out.println("Scheduling running now is: "+ nowString);
//        i++;
//        System.out.println("i :"+i);
//    }
//
//}