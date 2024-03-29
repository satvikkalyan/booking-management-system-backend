package com.example.bookingmanagementsystembackend.repository;


import com.example.bookingmanagementsystembackend.models.Payments;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends MongoRepository<Payments, ObjectId> {

}
