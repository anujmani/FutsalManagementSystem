package com.example.emailservice.controller;

import com.example.emailservice.model.Mail;
import com.example.emailservice.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailSenderService emailService;

    @PostMapping("/email")
    public void send(@RequestBody Mail mail) throws MessagingException {
        emailService.sendEmail(mail);
    }
}
