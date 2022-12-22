package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.business.resources.BookingResource;
import com.se.bookingmanagementsystembackend.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/bookings/")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("{userid}/{propertyid}/")
    public BookingResource saveNewBooking(@PathVariable Long userid,
                                          @PathVariable Long propertyid,
                                          @Valid @RequestBody BookingResource bookingResource) {
        return bookingService.saveNewBooking(userid, propertyid, bookingResource);
    }

    @GetMapping()
    public List<BookingResource> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("id/{id}/")
    public BookingResource getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("timestamp/{bookingTimestamp}")
    public BookingResource getBookingByBookTimestamp(@PathVariable String bookingTimestamp) {
        return bookingService.getBookingByBookTimestamp(bookingTimestamp);
    }

    @DeleteMapping("delete/{id}/")
    public void deleteBookingById(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
    }

    @PostMapping("checkout/{bookingId}/")
    public BookingResource checkoutUser(@PathVariable Long bookingId) {
        return bookingService.checkoutUser(bookingId);
    }


}
