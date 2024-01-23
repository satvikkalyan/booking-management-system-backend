package com.example.bookingmanagementsystembackend.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private ObjectId id;
    @DBRef
    private Property property;
    @DBRef
    private User user;
    @DBRef
    private Payments payment;
    private Date fromDate;
    private Date toDate;
    private String roomId;
}
