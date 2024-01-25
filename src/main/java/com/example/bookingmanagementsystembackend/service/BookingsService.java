package com.example.bookingmanagementsystembackend.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.example.bookingmanagementsystembackend.models.*;
import com.example.bookingmanagementsystembackend.models.datatransferobjects.BookingRequestObject;
import com.example.bookingmanagementsystembackend.repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class BookingsService {
    @Autowired
    private BookingsRepository bookingRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private RoomAvailabilityRepository roomAvailabilityRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private UserRepository userRepository;

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

    private Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }
    private static long getNumberOfDaysBetweenDates(Date fromDate, Date toDate) {
        long differenceInMilliseconds = toDate.getTime() - fromDate.getTime();
        return TimeUnit.DAYS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);
    }
    public Bookings createBooking(BookingRequestObject bookingRequestObject) {
            Optional<Property> property = propertyRepository.findById(new ObjectId(bookingRequestObject.getPropertyId()));
            Optional<User> user = userRepository.findById(new ObjectId(bookingRequestObject.getUserId()));
        if (property.get() != null && user.get() != null) {
            Date fromDate = convertStringToDate(bookingRequestObject.getFromDate());
            Date toDate = convertStringToDate(bookingRequestObject.getToDate());
            long numberOfDays = getNumberOfDaysBetweenDates(fromDate, toDate);
            Payments payment = new Payments(new ObjectId(),"PAID",(float) (((int)numberOfDays + 1)  * property.get().getPrice()),bookingRequestObject.getModeOfPayment());
            paymentsRepository.save(payment);
            Bookings booking = new Bookings(new ObjectId(),property.get(),user.get(),payment,fromDate,toDate,"");
            bookingRepository.save(booking);
            updateAvailability(property.get(), bookingRequestObject.getSelectedBeds(), bookingRequestObject.getToDate());
            return booking;
        } else {
            throw new RuntimeException("Property or User not found");
        }
    }

    private void updateAvailability(Property property, List<String> selectedBeds, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(toDate, formatter);
        List<RoomAvailability> roomAvailabilities =  property.getAvailability();
        for(RoomAvailability room:roomAvailabilities){
            if(selectedBeds.contains(room.getDirection()))
            room.setNextAvailabilityDate(localDate);
            roomAvailabilityRepository.save(room);
        }
        property.setAvailability(roomAvailabilities);
        propertyRepository.save(property);
    }

}
