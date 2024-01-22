package com.example.bookingmanagementsystembackend.repository;

import com.example.bookingmanagementsystembackend.models.Property;
import com.example.bookingmanagementsystembackend.models.Room;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, ObjectId> {
}
