package com.example.bookingmanagementsystembackend.repository;

import com.example.bookingmanagementsystembackend.models.Property;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends MongoRepository<Property, ObjectId> {
}
