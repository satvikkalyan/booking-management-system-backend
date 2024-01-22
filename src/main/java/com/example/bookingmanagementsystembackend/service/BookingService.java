package com.example.bookingmanagementsystembackend.service;


import com.example.bookingmanagementsystembackend.models.Bookings;
import com.example.bookingmanagementsystembackend.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    public BookingsRepository bookingsRepository;

    public Bookings createBooking(){
        System.out.println("Here");
        return new Bookings();
    }
}
