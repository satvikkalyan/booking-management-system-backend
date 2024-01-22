package com.example.bookingmanagementsystembackend.service;


import com.example.bookingmanagementsystembackend.models.Payments;
import com.example.bookingmanagementsystembackend.repository.PaymentsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentsService {
    @Autowired
    private PaymentsRepository paymentsRepository;

    public List<Payments> getAllPayments() {
        return paymentsRepository.findAll();
    }

    public Optional<Payments> getPaymentById(ObjectId id) {
        return paymentsRepository.findById(id);
    }

    public Payments createPayment(Payments payment) {
        return paymentsRepository.save(payment);
    }

}
