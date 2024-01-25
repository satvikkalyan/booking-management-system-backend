package com.example.bookingmanagementsystembackend.models.datatransferobjects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class BookingRequestObject {
    private String userId;
    private String fromDate;
    private String toDate;
    private String propertyId;
    private String modeOfPayment;
    private List<String> selectedBeds;
}
