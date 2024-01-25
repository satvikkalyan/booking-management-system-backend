package com.example.bookingmanagementsystembackend.repository;

import com.example.bookingmanagementsystembackend.models.RoomAvailability;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAvailabilityRepository extends MongoRepository<RoomAvailability, ObjectId> {
}
