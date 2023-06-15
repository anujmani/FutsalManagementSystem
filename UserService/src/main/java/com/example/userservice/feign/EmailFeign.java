package com.example.userservice.feign;

import com.example.userservice.entities.Mail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "EMAIL-SERVICE")
public interface EmailFeign {
    @PostMapping("/email")
    void sendEmail(Mail mail);

}
