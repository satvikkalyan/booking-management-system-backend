package com.se.bookingmanagementsystembackend.business.domain.Hotel;

import com.se.bookingmanagementsystembackend.business.resources.RoomTypeResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    private Integer bedsAvailable;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Property property;

    public RoomType(RoomTypeResource roomTypeResource, Property property) {
        this.id = roomTypeResource.getId();
        this.roomName = roomTypeResource.getRoomName();
        this.bedsAvailable = roomTypeResource.getBedsAvailable();
        this.property = property;
    }


}
