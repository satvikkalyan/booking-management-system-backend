package com.example.bookingmanagementsystembackend.repository;


import com.example.bookingmanagementsystembackend.models.Payments;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payments, ObjectId> {

    Optional<List<Payments>> findPaymentsByUserId(ObjectId userId);
}
