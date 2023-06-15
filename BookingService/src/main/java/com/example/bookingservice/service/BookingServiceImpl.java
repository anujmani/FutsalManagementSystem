package com.example.bookingservice.service;

import com.example.bookingservice.dto.BookingDto;
import com.example.bookingservice.dto.PaymentRequestDto;
import com.example.bookingservice.dto.PaymentResponseDto;
import com.example.bookingservice.entities.Booking;
import com.example.bookingservice.entities.Futsal;
import com.example.bookingservice.entities.FutsalEnum;
import com.example.bookingservice.entities.Mail;
import com.example.bookingservice.feignClient.FutsalClient;
import com.example.bookingservice.feignClient.MailClient;
import com.example.bookingservice.feignClient.PaymentClient;
import com.example.bookingservice.repository.BookingRepo;
import com.example.bookingservice.utils.JwtUtils;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private FutsalClient futsalClient;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PaymentClient paymentClient;
    @Autowired
    private MailClient mailClient;


    @Override
    public String bookCourt(BookingDto booking, String authhead) {
        Booking orginalBook = new Booking();
        orginalBook.setBookingId(booking.getBookingId());
        orginalBook.setBookingDate(booking.getBookingDate());
        orginalBook.setBookingTime(booking.getBookingTime());
        orginalBook.setFutsalid(booking.getFutsalId());
        orginalBook.setTime(booking.getTime());
        orginalBook.setEndtime(booking.getTime()+1);

        Futsal futsal = futsalClient.getFutsalById(booking.getFutsalId());
        String token = authhead.replace("Bearer ", "");
        orginalBook.setUserEmail(jwtUtils.extractUser(token));

        if (futsal.getFutsalEnum() == FutsalEnum.AVAILABLE) {


            PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
            paymentRequestDto.setAmount(10000);
            PaymentResponseDto paymentResponseDto = paymentClient.payment(paymentRequestDto);

            log.info("Payment done of " + paymentResponseDto.getAmount());

            futsalClient.updateFutsal(booking.getFutsalId());
            log.info("Sending mail to the user after booking");
            Mail mail = new Mail();
            mail.setToEmail(orginalBook.getUserEmail());
            mail.setBody("Thank you for booking the field.");
            mail.setMessage("Mail is sent");
            mail.setSubject("Welcome to futsal Booking System");
            mailClient.sendEmail(mail);
            bookingRepo.save(orginalBook);


            return "booked";
        } else {
            throw new RuntimeException("Already booked");
        }
    }

    public BookingDto getBookingById(int id) {
        Booking book = bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("No booking"));
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(book.getBookingId());
        bookingDto.setBookingDate(book.getBookingDate());
        bookingDto.setBookingTime(book.getBookingTime());
        return bookingDto;
    }
}
