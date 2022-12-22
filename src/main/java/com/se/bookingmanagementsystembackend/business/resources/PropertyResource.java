package com.se.bookingmanagementsystembackend.business.resources;

import com.se.bookingmanagementsystembackend.business.domain.Hotel.Property;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PropertyResource {

    private Long id;

    private String propertyName;

    private String location;

    private String city;

    private String state;

    private String zipCode;

    private String status;

    private Float price;

    private String rating;

    private String imageSrc;

    private List<RoomTypeResource> roomTypeResourceList;

    private String type;

    private List<BookingResource> bookingResources;

    private String description;

    private String roomFacilities;

    private LocalDate availableFrom;

    private LocalDate availableTo;

    private String descriptionTitle;

    private String highlightTitle;

    private String highlightDescription;

    private String imageSource1;
    private String imageSource2;
    private String imageSource3;
    private String imageSource4;

    public PropertyResource(Property property) {
        this.id = property.getId();
        this.propertyName = property.getPropertyName();
        this.location = property.getLocation();
        this.city = property.getCity();
        this.state = property.getState();
        this.zipCode = property.getZipcode();
        this.status = property.getStatus();
        this.price = property.getPrice();
        this.rating = property.getRating();
        this.imageSrc = property.getImageSrc();
        this.roomTypeResourceList = property.getRoomTypeList()
                .stream()
                .map(RoomTypeResource::new)
                .collect(Collectors.toList());
        this.type = property.getType();
        this.bookingResources = property.getBookings()
                .stream().map(BookingResource::new)
                .collect(Collectors.toList());
        this.description = property.getDescription();
        this.roomFacilities = property.getRoomFacilities();
        this.availableFrom = property.getAvailableFrom();
        this.availableTo = property.getAvailableTo();
        this.descriptionTitle = property.getDescriptionTitle();
        this.highlightTitle = property.getHighlightTitle();
        this.highlightDescription = property.getHighlightDescription();
        this.imageSource1 = property.getImageSource1();
        this.imageSource2 = property.getImageSource2();
        this.imageSource3 = property.getImageSource3();
        this.imageSource4 = property.getImageSource4();
    }
}
