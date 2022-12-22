package com.se.bookingmanagementsystembackend.service;

import com.se.bookingmanagementsystembackend.business.domain.booking.Booking;
import com.se.bookingmanagementsystembackend.business.resources.BookingResource;
import com.se.bookingmanagementsystembackend.exception.UserAlreadyCheckoutException;
import com.se.bookingmanagementsystembackend.repository.BookingRepository;
import com.se.bookingmanagementsystembackend.repository.PropertyRepository;
import com.se.bookingmanagementsystembackend.repository.RoomTypeRepository;
import com.se.bookingmanagementsystembackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class BookingService {


    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    private final RoomTypeRepository roomTypeRepository;

    private final PaymentService paymentService;

    public BookingResource saveNewBooking(Long userId, Long propertyId, BookingResource bookingResource) {
        var user = userRepository.findById(userId);
        var property = propertyRepository.findById(propertyId);

        if (user.isPresent() && property.isPresent()) {
            int totalRoomsCount = deductRoomsCount(bookingResource, propertyId);
            bookingResource.setStatus("IN");
            var newBooking = new Booking(bookingResource, user.get(), property.get(), null);
            bookingRepository.save(newBooking);
            paymentService.savePaymentDetails(bookingResource, propertyId, newBooking, totalRoomsCount);
            return new BookingResource(newBooking);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User or Property ID is invalid");
        }
    }

    @Transactional
    public int deductRoomsCount(BookingResource bookingResource, Long propertyId) {
        String[] roomTypeSplit = bookingResource.getRoomTypeCount().split(",");
        int totalCount = 0;
        for (String entry : roomTypeSplit) {
            roomTypeRepository.deductRoomCount(propertyId, entry.split(":")[0], Integer.parseInt(entry.split(":")[1]));
            totalCount += Integer.parseInt(entry.split(":")[1]);
        }
        return totalCount;
    }

    public List<BookingResource> getAllBookings() {
        return bookingRepository.findAll().stream().map(BookingResource::new).collect(Collectors.toList());
    }

    public BookingResource getBookingById(Long id) {
        var booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return new BookingResource(booking.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking ID not found in DB");
        }
    }

    public BookingResource getBookingByBookTimestamp(String bookingTimestamp) {
        var booking = bookingRepository.findByBookingTimestamp(bookingTimestamp);
        if (booking.isPresent()) {
            return new BookingResource(booking.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking timestamp invalid. No record found");
        }
    }

    @Transactional
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    @Transactional
    public BookingResource checkoutUser(Long bookingId) {
        var bookingData = bookingRepository.findById(bookingId);

        if (bookingData.isPresent()) {
            if (bookingData.get().getStatus().equals("OUT")) {
                throw new UserAlreadyCheckoutException("User is already checked out. Cannot checkout again");
            } else {
                String[] rooms = bookingData.get().getRoomTypeCount().split(",");
                bookingRepository.markBookingStatusAsOut(bookingId);
                String propertyId = bookingRepository.getPropertyId(bookingId);
                for (String entry : rooms) {
                    roomTypeRepository.increaseBedCount(entry.split(":")[0], Integer.parseInt(entry.split(":")[1]), Long.valueOf(propertyId));
                }
                return new BookingResource(bookingRepository.findById(bookingId).get());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Booking ID not found");
        }
    }
}
