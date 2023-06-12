package com.example.bookingservice.controller;

import com.example.bookingservice.dto.BookingDto;
import com.example.bookingservice.entities.Booking;
import com.example.bookingservice.service.BookingService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("/bookCourt")
    private void bookId(@RequestBody BookingDto booking, @RequestHeader("Authorization")String authorizationHeader){
        bookingService.bookCourt(booking,authorizationHeader);
    }

}
