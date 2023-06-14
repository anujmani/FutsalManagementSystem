package com.example.bookingservice.feignClient;

import com.example.bookingservice.dto.PaymentRequestDto;
import com.example.bookingservice.dto.PaymentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value="external", url = "https://2c8c93e2-71ba-4c21-afef-d8e8871106df.mock.pstmn.io")
public interface PaymentClient {
//    @PostMapping("/paymentService")
//    PaymentResponseDto payment(@RequestBody PaymentRequestDto paymentRequestDto);

    @PostMapping("/payBooking")
    PaymentResponseDto payment(@RequestBody PaymentRequestDto paymentRequestDto);

}
