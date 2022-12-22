package com.se.bookingmanagementsystembackend.service;

import com.se.bookingmanagementsystembackend.business.domain.booking.Booking;
import com.se.bookingmanagementsystembackend.business.domain.payment.Payment;
import com.se.bookingmanagementsystembackend.business.resources.BookingResource;
import com.se.bookingmanagementsystembackend.repository.PaymentRepository;
import com.se.bookingmanagementsystembackend.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PropertyRepository propertyRepository;

    public void savePaymentDetails(BookingResource bookingResource, Long propertyId, Booking newBooking, int totalRoomsCount) {
        var newPayment = new Payment();
        newPayment.setPaymentDate(LocalDate.now());
        newPayment.setAmount(calculateFinalAmount(propertyId, calculateTotalDays(bookingResource.getFromDate(), bookingResource.getToDate()), totalRoomsCount));
        newPayment.setBooking(newBooking);
        newPayment.setPaymentType(bookingResource.getPaymentType());
        paymentRepository.save(newPayment);
    }


    public float calculateFinalAmount(Long propertyId, int totalDays, int totalRoomsCount) {
        System.out.println("Property Id: " + propertyId);
        System.out.println("total days: " + totalDays);
        System.out.println("total rooms count: " + totalRoomsCount);
        var perNightAmount = propertyRepository.getPerDayAmount(propertyId);
        var temp = (perNightAmount * totalDays * totalRoomsCount);
        System.out.println(temp);
        return temp;
    }

    public int calculateTotalDays(LocalDate fromDate, LocalDate toDate) {
        return Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(fromDate, toDate)));
    }
}
