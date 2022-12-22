package com.se.bookingmanagementsystembackend.service;

//import com.SpringBootEmail.Entity.EmailDetails;

import com.se.bookingmanagementsystembackend.business.resources.PromotionResource;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SubscriptionService subscriptionService;

    @Value("${company-email}")
    private String noReplyEmail;

    public void sendEmail() {
        SimpleMailMessage newMessage = new SimpleMailMessage();
        newMessage.setTo("abc@gmail.com");
        newMessage.setSubject("Test DSRV");
        newMessage.setText("Hello World");

        javaMailSender.send(newMessage);
    }


    public void sendPromotionEmail(PromotionResource promotionResource) {
        var emails = subscriptionService.getAllSubscribedUsers();
        List<String> recipients = new ArrayList<>();
        for (var entry : emails) {
            recipients.add(entry.getEmail());
        }
        try {
            String[] arr = new String[recipients.size()];
            arr = recipients.toArray(arr);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setText(promotionResource.getPromotionMessage());
            simpleMailMessage.setSubject(promotionResource.getPromotionTitle());
            simpleMailMessage.setBcc(arr);
            simpleMailMessage.setReplyTo(noReplyEmail);
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
