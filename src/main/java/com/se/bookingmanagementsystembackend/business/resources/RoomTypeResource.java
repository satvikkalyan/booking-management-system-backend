package com.se.bookingmanagementsystembackend.business.resources;

import com.se.bookingmanagementsystembackend.business.domain.Hotel.RoomType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomTypeResource {

    private Long id;

    private String roomName;

    private Integer bedsAvailable;

    public RoomTypeResource(RoomType roomType) {
        this.id = roomType.getId();
        this.roomName = roomType.getRoomName();
        this.bedsAvailable = roomType.getBedsAvailable();
    }
}
