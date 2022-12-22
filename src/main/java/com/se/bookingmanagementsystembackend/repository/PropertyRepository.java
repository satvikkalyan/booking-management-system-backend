package com.se.bookingmanagementsystembackend.repository;

import com.se.bookingmanagementsystembackend.business.domain.Hotel.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findAllByCity(String city);

    Optional<Property> findById(Long id);

    @Query(value = "SELECT city as city, count(*) AS count FROM property GROUP BY city", nativeQuery = true)
    List<String> getPropertyCountByCities();

    @Query(value = "SELECT type , count(*) AS count FROM property GROUP BY type", nativeQuery = true)
    List<String> getPropertyCountByTypes();

    @Query(value = "SELECT price FROM property WHERE id = ?1", nativeQuery = true)
    float getPerDayAmount(Long propertyId);

    List<Property> findAllByPropertyNameIn(List<String> propertyNames);


    @Modifying
    @Transactional
    @Query(value = "UPDATE property SET status = 'Unavailable' WHERE id = ?1", nativeQuery = true)
    void updatePropertyAsUnavailable(Long propertyId);

    @Query(value = "SELECT rt.property_id FROM room_type rt WHERE rt.property_id IN\n" +
            "(\n" +
            "SELECT p.id FROM dsrv.property p INNER JOIN room_type rt\n" +
            "ON p.id = rt.property_id \n" +
            "WHERE p.available_from >= ?1 and p.available_to <= ?2 \n" +
            " ) and rt.beds_available >= ?3", nativeQuery = true)
    List<Long> findAllPropertiesIdByDatesAndOccupancy(LocalDate fromDate, LocalDate toDate, Integer occupancy);

    List<Property> findAllByIdIn(List<Long> propertyIds);

}
