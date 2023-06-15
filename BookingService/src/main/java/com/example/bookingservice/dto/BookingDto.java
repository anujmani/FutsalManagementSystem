package com.example.bookingservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private int bookingId;
    private String bookingTime;
    private LocalDate bookingDate;
    private int FutsalId;
    private Integer time;
}
