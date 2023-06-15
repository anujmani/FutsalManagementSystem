package com.example.bookingservice.bookingtest;

import com.example.bookingservice.dto.BookingDto;
import com.example.bookingservice.dto.PaymentRequestDto;
import com.example.bookingservice.dto.PaymentResponseDto;
import com.example.bookingservice.entities.Booking;
import com.example.bookingservice.entities.Futsal;
import com.example.bookingservice.entities.Mail;
import com.example.bookingservice.feignClient.FutsalClient;
import com.example.bookingservice.feignClient.MailClient;
import com.example.bookingservice.feignClient.PaymentClient;
import com.example.bookingservice.repository.BookingRepo;
import com.example.bookingservice.service.BookingService;
import com.example.bookingservice.service.BookingServiceImpl;
import com.example.bookingservice.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingTest {
    @Mock
    private FutsalClient futsalClient;

    @Mock
    private PaymentClient paymentClient;

    @Mock
    private MailClient mailClient;

    @Mock
    private BookingRepo bookingRepo;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private BookingServiceImpl bookingService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBookCourt_WhenFutsalIsAvailable_ShouldBookCourtAndReturnSuccessMessage() {
        // Arrange
        BookingDto booking = new BookingDto();
        // Set up the necessary properties of the booking object

        String authHead = "Bearer <token>";
        String userEmail = "arowanuj@gmail.com";
        int futsalId = 123456;

        Futsal futsal = new Futsal();
        // Set up the necessary properties of the futsal object

        PaymentResponseDto paymentResponse = new PaymentResponseDto();
        paymentResponse.setAmount(10000);

        Mail mail = new Mail();

        when(futsalClient.getFutsalById(futsalId)).thenReturn(futsal);
        when(jwtUtils.extractUser(anyString())).thenReturn(userEmail);
        when(paymentClient.payment(any(PaymentRequestDto.class))).thenReturn(paymentResponse);
        doNothing().when(futsalClient).updateFutsal(futsalId);
        doNothing().when(mailClient).sendEmail(mail);
        when(bookingRepo.save(any(Booking.class))).thenReturn(new Booking());

        // Act
        String result = bookingService.bookCourt(booking, authHead);

        // Assert
        assertEquals("booked", result);
        verify(futsalClient).getFutsalById(futsalId);
        verify(jwtUtils).extractUser(anyString());
        verify(paymentClient).payment(any(PaymentRequestDto.class));
        verify(futsalClient).updateFutsal(futsalId);
        verify(mailClient).sendEmail(mail);
        verify(bookingRepo).save(any(Booking.class));
    }


}
