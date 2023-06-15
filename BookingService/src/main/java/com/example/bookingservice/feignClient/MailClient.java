package com.example.bookingservice.feignClient;

import com.example.bookingservice.entities.Mail;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "EMAIL-SERVICE")
public interface MailClient {
    @PostMapping("/email")
    void sendEmail(Mail mail);
}
