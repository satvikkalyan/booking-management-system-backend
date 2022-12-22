package com.se.bookingmanagementsystembackend.business.resources;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.se.bookingmanagementsystembackend.business.domain.booking.Booking;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookingResource {

    private Long id;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer occupancy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Pattern(regexp = "IN|OUT")
    private String status;

    @Pattern(regexp = "Card|Cash")
    private String paymentType;

    //    private List<BookingRoomResource> bookingRoomResource;
    private String roomTypeCount;

    private String bookingTimestamp;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime checkoutTimestamp;


    public BookingResource(Booking booking) {
        this.id = booking.getId();
        this.fromDate = booking.getFromDate();
        this.toDate = booking.getToDate();
        this.status = booking.getStatus();
        this.occupancy = booking.getOccupancy();
        this.roomTypeCount = booking.getRoomTypeCount();
        this.paymentType = booking.getPaymentType();
        this.bookingTimestamp = booking.getBookingTimestamp();
        this.checkoutTimestamp = booking.getCheckoutTimestamp();
    }


}
