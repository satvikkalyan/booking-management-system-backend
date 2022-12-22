package com.se.bookingmanagementsystembackend.business.domain.payment;

import com.se.bookingmanagementsystembackend.business.domain.booking.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private float amount;

    private LocalDate paymentDate;

    private String paymentType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Booking booking;


}
