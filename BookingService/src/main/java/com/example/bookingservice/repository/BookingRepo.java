package com.example.bookingservice.repository;

import com.example.bookingservice.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking,Integer> {
}
