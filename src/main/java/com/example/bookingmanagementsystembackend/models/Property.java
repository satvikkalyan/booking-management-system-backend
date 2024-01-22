package com.example.bookingmanagementsystembackend.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Document(collection = "property")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId propertyId;
    private String propertyType;
    private String propertyName;
    private String propertyDescription;
    private String propertyFacilities;
    private int rating;
    private double price;
    private String description;
    private String street;
    private String shortTitle;
    private String shortDescription;
    @DBRef
    @Field("rooms")
    private Room room;
    private String city;
    private List<String> images;
}