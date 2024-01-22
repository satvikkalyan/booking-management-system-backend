package com.example.bookingmanagementsystembackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payments {
    @Id
    private ObjectId paymentId;
    private ObjectId userId;
    private ObjectId propertyId;
    private String status;
    private Float amount;
}
