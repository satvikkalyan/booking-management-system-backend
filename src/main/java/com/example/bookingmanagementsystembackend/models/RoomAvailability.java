package com.example.bookingmanagementsystembackend.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String nextAvailabilityDate;
    public RoomAvailability(ObjectId roomId, String direction, String nextAvailableDate) {
        this.id = roomId;
        this.direction = direction;
        this.nextAvailabilityDate = nextAvailableDate;
    }
}
