package com.se.bookingmanagementsystembackend.business.domain.booking;

import com.se.bookingmanagementsystembackend.business.domain.Hotel.Property;
import com.se.bookingmanagementsystembackend.business.domain.payment.Payment;
import com.se.bookingmanagementsystembackend.business.domain.user.User;
import com.se.bookingmanagementsystembackend.business.resources.BookingResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer occupancy;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Property property;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "booking")
    private Payment payment;

    private String status;

//    private List<String> roomNames;

    private String roomTypeCount;

    private String paymentType;

    private String bookingTimestamp;

    private LocalDateTime checkoutTimestamp;


    public Booking(BookingResource bookingResource, User user, Property property, Payment payment) {
        this.id = bookingResource.getId();
        this.fromDate = bookingResource.getFromDate();
        this.toDate = bookingResource.getToDate();
        this.user = user;
        this.property = property;
        this.status = bookingResource.getStatus();
        this.payment = payment;
        this.occupancy = bookingResource.getOccupancy();
        this.roomTypeCount = bookingResource.getRoomTypeCount();
        this.paymentType = bookingResource.getPaymentType();
        this.bookingTimestamp = bookingResource.getBookingTimestamp();
        this.checkoutTimestamp = bookingResource.getCheckoutTimestamp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Booking booking = (Booking) o;
        return id != null && Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
