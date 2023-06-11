package com.example.bookingservice.service;

import com.example.bookingservice.entities.Booking;
import com.example.bookingservice.repository.BookingRepo;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public void bookCourt(Booking booking) {
        bookingRepo.save(booking);

    }
}
