package com.example.bookingmanagementsystembackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "property")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    private ObjectId propertyId;
    private String propertyType;
    private String propertyName;
    private String propertyDescription;
}
