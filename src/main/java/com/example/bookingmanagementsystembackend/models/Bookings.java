package com.example.bookingmanagementsystembackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
    @Id
    private ObjectId bookingId;
    private String paymentId;
    private String propertyId;
}
