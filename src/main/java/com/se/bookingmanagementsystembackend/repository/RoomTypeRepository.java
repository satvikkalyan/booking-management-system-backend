package com.se.bookingmanagementsystembackend.repository;

import com.se.bookingmanagementsystembackend.business.domain.Hotel.RoomType;
import org.hibernate.jpa.QueryHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    List<RoomType> findRoomTypeByPropertyIdAndRoomName(Long id, String name);

    List<RoomType> findRoomTypeByPropertyId(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE room_type SET beds_available = (beds_available - ?3) WHERE property_id = ?1 and room_name = ?2", nativeQuery = true)
    void deductRoomCount(Long id, String roomName, Integer occupancy);

    @Modifying
    @Transactional
    @Query(value = "UPDATE room_type SET beds_available = beds_available + 1 WHERE property_id = ?1", nativeQuery = true)
    void increaseRoomCount(Long id);

    @org.springframework.data.jpa.repository.QueryHints(value = {
            @QueryHint(name = QueryHints.HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE),
            @QueryHint(name = QueryHints.HINT_CACHEABLE, value = "false")})
    @Query(value = "SELECT beds_available as Beds, p.property_name FROM room_type rt \n" +
            "INNER JOIN property p\n" +
            "ON\n" +
            "p.id = rt.property_id ", nativeQuery = true)
    List<String> noOfRoomsForEachProperty();

    @org.springframework.data.jpa.repository.QueryHints(value = {
            @QueryHint(name = QueryHints.HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE),
            @QueryHint(name = QueryHints.HINT_CACHEABLE, value = "false")})
    @Query(value = "SELECT beds_available as Beds, p.property_name FROM room_type rt \n" +
            "INNER JOIN property p\n" +
            "ON\n" +
            "p.id = rt.property_id WHERE p.status = 'Available'", nativeQuery = true)
    List<String> fetchAvailableProperties();

    @Modifying
    @Query(value = "UPDATE room_type SET beds_available = beds_available + ?2 WHERE room_name = ?1 AND property_id = ?3",nativeQuery = true)
    void increaseBedCount(String roomName, int count, Long propertyId);

}
