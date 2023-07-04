package com.example.demo.Demo.DemoMail.MailController;

import java.io.File;


import com.example.demo.Demo.DemoMail.Mail.MyConstants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AttachmentEmailExampleController {

    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @PostMapping("/sendAttachmentEmail")
    public String sendAttachmentEmail() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);

        helper.setTo(MyConstants.FRIEND_EMAIL);
        helper.setSubject("Test email with attachments");
        helper.setText("Hello, Im testing email with attachments!");

        String path1 = "C:/Users/dokho/Downloads/BoxChatER.png";
       // String path2 = "/home/tran/Downloads/readme.zip";

        // Attachment 1
        FileSystemResource file1 = new FileSystemResource(new File(path1));
        helper.addAttachment("Txt file", file1);

        // Attachment 2
//        FileSystemResource file2 = new FileSystemResource(new File(path2));
//        helper.addAttachment("Readme", file2);

        emailSender.send(message);

        return "Email Sent!";
    }

}