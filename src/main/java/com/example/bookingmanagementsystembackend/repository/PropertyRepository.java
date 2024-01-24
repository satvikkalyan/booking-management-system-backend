package com.example.bookingmanagementsystembackend.repository;

import com.example.bookingmanagementsystembackend.models.Property;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PropertyRepository extends MongoRepository<Property, ObjectId> {
    @Query("{'city': ?0, 'availability': {$ne: []}}")
    List<Property> findPropertiesByCityAndNonEmptyAvailability(String city);

}
