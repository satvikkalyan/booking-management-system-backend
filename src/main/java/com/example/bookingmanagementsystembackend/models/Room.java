package com.example.bookingmanagementsystembackend.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private boolean north;
    private boolean south;
    private boolean west;
    private boolean east;
}
