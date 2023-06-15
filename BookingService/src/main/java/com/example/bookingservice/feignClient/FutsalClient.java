package com.example.bookingservice.feignClient;

import com.example.bookingservice.entities.Futsal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "FUTSAL-REGISTER")
public interface FutsalClient {
    @GetMapping("futsal/getFutsal/{id}")
    Futsal getFutsalById(@PathVariable("id") int id);

    @PostMapping("futsal/updateStatus/{id}")
    String updateFutsal(@PathVariable("id") int id);

}
