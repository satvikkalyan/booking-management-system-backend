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
    private List<RoomAvailability> availability;
    private String city;
    private List<String> images;

    public Property(Property property) {
        this.propertyId = property.getPropertyId();
        this.propertyType = property.getPropertyType();
        this.propertyName = property.getPropertyName();
        this.propertyDescription = property.getPropertyDescription();
        this.propertyFacilities = property.getPropertyFacilities();
        this.rating = property.getRating();
        this.price = property.getPrice();
        this.description = property.getDescription();
        this.street = property.getStreet();
        this.shortTitle = property.getShortTitle();
        this.shortDescription = property.getShortDescription();
        this.availability = property.getAvailability();
        this.city = property.getCity();
        this.images = property.getImages();
    }
}