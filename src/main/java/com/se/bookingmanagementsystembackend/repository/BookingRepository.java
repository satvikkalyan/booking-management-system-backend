package com.se.bookingmanagementsystembackend.repository;

import com.se.bookingmanagementsystembackend.business.domain.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingTimestamp(String bookingTimestamp);

    Optional<Booking> findById(Long id);

    @Modifying
    @Query(value = "UPDATE booking SET status = 'OUT', checkout_timestamp = NOW() WHERE id = ?1 ", nativeQuery = true)
    void markBookingStatusAsOut(Long id);

    @Query(value = "SELECT property_id FROM booking WHERE id = ?1", nativeQuery = true)
    String getPropertyId(Long id);


}
