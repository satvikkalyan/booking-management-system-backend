package com.example.bookingmanagementsystembackend.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Document(collection = "roomAvailability")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAvailability {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId propertyId;
    private String direction;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate nextAvailabilityDate;
    public RoomAvailability(ObjectId roomId, String direction, LocalDate nextAvailableDate) {
        this.id = roomId;
        this.direction = direction;
        this.nextAvailabilityDate = nextAvailableDate;
    }
}
