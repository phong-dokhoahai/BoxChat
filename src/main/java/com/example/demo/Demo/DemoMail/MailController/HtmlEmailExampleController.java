package com.example.demo.Demo.DemoMail.MailController;


import com.example.demo.Demo.DemoMail.Mail.MyConstants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.nio.charset.StandardCharsets;


@RestController
public class HtmlEmailExampleController {


    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @PostMapping("/sendHtmlEmail")
    public String sendHtmlEmail() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String path = "classpath:templates/login.html";
        String content = null;
        File file = null;
        try {
            file = ResourceUtils.getFile(path);
            InputStream in = new FileInputStream(file);
            content = new String(in.readAllBytes(), StandardCharsets.UTF_8);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try (InputStream is = getClass().getResourceAsStream(path)) {
//            content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String htmlMsg = content;

        message.setContent(htmlMsg, "text/html");

        helper.setTo(MyConstants.FRIEND_EMAIL);
        helper.setSubject("Test send HTML email");

        this.emailSender.send(message);

        return "Email Sent!";
    }
}