package com.example.bookingmanagementsystembackend.service;


import com.example.bookingmanagementsystembackend.models.Bookings;
import com.example.bookingmanagementsystembackend.models.Payments;
import com.example.bookingmanagementsystembackend.repository.BookingsRepository;
import com.example.bookingmanagementsystembackend.repository.PaymentsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BookingsService {
    @Autowired
    private BookingsRepository bookingRepository;
    @Autowired
    private PaymentsRepository paymentsRepository;

    public Optional<List<Bookings>> getAllBookings() {
        return Optional.of(bookingRepository.findAll());
    }

    public Bookings getBookingById(ObjectId id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Bookings createBooking(Bookings booking) {
        return bookingRepository.save(booking);
    }
    public void deleteBooking(ObjectId id){
        Optional<Bookings> bookingOptional = bookingRepository.findById(id);

        if (bookingOptional.isPresent()) {
            Bookings booking = bookingOptional.get();
            Payments payment = booking.getPayment();
            if (payment != null) {
                paymentsRepository.deleteById(payment.getPaymentId());
            }
            bookingRepository.deleteById(id);
        }
    }
}
