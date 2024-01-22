package com.example.bookingmanagementsystembackend.service;


import com.example.bookingmanagementsystembackend.models.Payments;
import com.example.bookingmanagementsystembackend.models.User;
import com.example.bookingmanagementsystembackend.repository.BookingsRepository;
import com.example.bookingmanagementsystembackend.repository.PaymentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    public PaymentRepository paymentRepository;


    public Optional<List<Payments>>  getUsersPayments(ObjectId id){
        return paymentRepository.findPaymentsByUserId(id);
    }
}
