package com.example.bookingservice.controller;

import com.example.bookingservice.entities.Booking;
import com.example.bookingservice.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookCourt")
    private void bookId(@RequestBody Booking booking){
        bookingService.bookCourt(booking);

    }
}
