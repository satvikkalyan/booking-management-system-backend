package com.example.bookingmanagementsystembackend.models.datatransferobjects;


import lombok.Data;

@Data
public class PropertySearchRequest {
    private String destination;
    private String startDate;
    private String endDate;
    private int numberOfBeds;

}
