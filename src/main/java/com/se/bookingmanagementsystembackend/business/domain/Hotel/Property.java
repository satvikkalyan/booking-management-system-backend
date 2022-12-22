package com.se.bookingmanagementsystembackend.business.domain.Hotel;

import com.se.bookingmanagementsystembackend.business.domain.booking.Booking;
import com.se.bookingmanagementsystembackend.business.resources.PropertyResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propertyName;

    private String location;

    private String city;

    private String state;

    private String zipcode;

    private String status;

    private Float price;

    private String rating;

    private String imageSrc;

    private String description;

    private String roomFacilities;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<RoomType> roomTypeList;

    private String type;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    private LocalDate availableFrom;

    private LocalDate availableTo;

    private String descriptionTitle;

    private String highlightTitle;

    private String highlightDescription;

    private String imageSource1;
    private String imageSource2;
    private String imageSource3;
    private String imageSource4;

    public Property(PropertyResource propertyResource) {
        this.id = propertyResource.getId();
        this.propertyName = propertyResource.getPropertyName();
        this.location = propertyResource.getLocation();
        this.city = propertyResource.getCity();
        this.state = propertyResource.getState();
        this.zipcode = propertyResource.getZipCode();
        this.status = propertyResource.getStatus();
        this.price = propertyResource.getPrice();
        this.rating = propertyResource.getRating();
        this.imageSrc = propertyResource.getImageSrc();
        this.roomTypeList = propertyResource.getRoomTypeResourceList()
                .stream()
                .map(roomTypeResource -> new RoomType(roomTypeResource, this))
                .collect(Collectors.toList());
        this.type = propertyResource.getType();
        this.bookings = propertyResource.getBookingResources()
                .stream()
                .map(bookingResource -> new Booking(bookingResource, null, this, null))
                .collect(Collectors.toList());

        this.description = propertyResource.getDescription();
        this.roomFacilities = propertyResource.getRoomFacilities();
        this.availableFrom = propertyResource.getAvailableFrom();
        this.availableTo = propertyResource.getAvailableTo();
        this.descriptionTitle = propertyResource.getDescriptionTitle();
        this.highlightTitle = propertyResource.getHighlightTitle();
        this.highlightDescription = propertyResource.getHighlightDescription();
        this.imageSource1 = propertyResource.getImageSource1();
        this.imageSource2 = propertyResource.getImageSource2();
        this.imageSource3 = propertyResource.getImageSource3();
        this.imageSource4 = propertyResource.getImageSource4();

    }

}
