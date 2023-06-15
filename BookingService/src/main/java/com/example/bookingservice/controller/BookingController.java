package com.example.bookingservice.controller;

import com.example.bookingservice.dto.BookingDto;
import com.example.bookingservice.entities.Booking;
import com.example.bookingservice.service.BookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

//    @CircuitBreaker(name = "BookingBreaker", fallbackMethod = "BookingFallBackMethod")
    @PostMapping("/bookCourt")
    private String bookId(@RequestBody @Valid BookingDto booking, @RequestHeader("Authorization") String authorizationHeader) {
        return bookingService.bookCourt(booking, authorizationHeader);
    }
//    public ResponseEntity<BookingDto> BookingFallBackMethod(@RequestBody BookingDto bookingDto,String authorizationHeader){
//        bookingDto= BookingDto.builder().bookingTime("1hr").bookingDate(java.time.LocalDate.now()).time(00).build();
//        return new ResponseEntity<>(bookingDto, HttpStatus.OK);
//    }

}
