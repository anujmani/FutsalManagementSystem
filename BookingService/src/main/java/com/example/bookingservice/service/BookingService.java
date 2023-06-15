package com.example.bookingservice.service;

import com.example.bookingservice.dto.BookingDto;
import com.example.bookingservice.entities.Booking;
import org.apache.tomcat.util.http.parser.Authorization;

public interface BookingService {
    String bookCourt(BookingDto booking, String token);

    BookingDto getBookingById(int id);
}
