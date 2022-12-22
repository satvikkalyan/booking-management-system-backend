package com.se.bookingmanagementsystembackend.repository;

import com.se.bookingmanagementsystembackend.business.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT sum(amount) FROM dsrv.payment WHERE payment_date BETWEEN ?1 AND ?2",nativeQuery = true)
    Integer calculateRevenueBetweenDateRange(LocalDate fromDate, LocalDate toDate);

    @Query(value = "SELECT sum(amount) FROM payment WHERE payment_date = ?1",nativeQuery = true)
    Integer getTodaysRevenue(LocalDate todayDate);

}
