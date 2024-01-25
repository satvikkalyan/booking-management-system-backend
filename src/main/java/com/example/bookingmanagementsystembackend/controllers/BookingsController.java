package com.example.bookingmanagementsystembackend.controllers;


import com.example.bookingmanagementsystembackend.models.Bookings;
import com.example.bookingmanagementsystembackend.models.datatransferobjects.BookingRequestObject;
import com.example.bookingmanagementsystembackend.service.BookingsService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @GetMapping
    public ResponseEntity<List<Bookings>> getAllBookings() {
        Optional<List<Bookings>> bookingsOptional = bookingsService.getAllBookings();
        if (bookingsOptional.isPresent()) {
            List<Bookings> bookings = bookingsOptional.get();
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookings> getBookingsById(@PathVariable ObjectId id) {
        Bookings bookings = bookingsService.getBookingById(id);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @PostMapping("/book")
    public ResponseEntity<Bookings> createBookings(@RequestBody BookingRequestObject bookingRequestObject) {
        if(bookingRequestObject.getFromDate().length()>0 && bookingRequestObject.getToDate().length()>0 && bookingRequestObject.getPropertyId().length()>0
        && bookingRequestObject.getModeOfPayment().length()>0) {
            Bookings bookings = bookingsService.createBooking(bookingRequestObject);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping
    public ResponseEntity<Bookings> createBookings(@RequestBody Bookings bookings) {
        Bookings createdBookings = bookingsService.createBooking(bookings);
        return new ResponseEntity<>(createdBookings, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Bookings> deleteBooking(@RequestBody ObjectId id) {
        bookingsService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
    
}
