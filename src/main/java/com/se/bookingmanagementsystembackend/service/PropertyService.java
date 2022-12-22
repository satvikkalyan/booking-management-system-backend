package com.se.bookingmanagementsystembackend.service;

import com.se.bookingmanagementsystembackend.business.domain.Hotel.Property;
import com.se.bookingmanagementsystembackend.business.resources.PropertyResource;
import com.se.bookingmanagementsystembackend.business.resources.RoomTypeResource;
import com.se.bookingmanagementsystembackend.repository.PropertyRepository;
import com.se.bookingmanagementsystembackend.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final RoomTypeRepository roomTypeRepository;

    public List<PropertyResource> fetchAllHotels() {
        return propertyRepository.findAll().stream().map(PropertyResource::new).collect(Collectors.toList());
    }

    public List<PropertyResource> fetchHotelsByCity(String city) {
        List<Property> properties = propertyRepository.findAllByCity(city);

        if (properties.size() > 0) {
            return properties.stream().map(PropertyResource::new).collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotels not Found for given city");
        }
    }

    @Transactional
    public PropertyResource saveNewHotel(PropertyResource propertyResource) {

        return new PropertyResource(propertyRepository.save(new Property(propertyResource)));
    }

    public PropertyResource getHotelById(Long id) {
        Optional<Property> hotelOptional = propertyRepository.findById(id);
        if (hotelOptional.isPresent()) {
            return new PropertyResource(hotelOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel for given ID not found");
        }
    }

    @Transactional
    public void deleteHotelById(Long id) {
        Optional<Property> hotelOptional = propertyRepository.findById(id);
        hotelOptional.ifPresent(propertyRepository::delete);
        log.info("Hotel Deleted Successfully");
    }

    public List<String> getCountByCities() {
        return propertyRepository.getPropertyCountByCities();

    }

    public List<String> getCountByTypes() {
        return propertyRepository.getPropertyCountByTypes();

    }

    public List<RoomTypeResource> getAllRoomsForPropertyById(Long id) {
        return roomTypeRepository.findRoomTypeByPropertyId(id).stream().map(RoomTypeResource::new).collect(Collectors.toList());
    }

    public List<PropertyResource> filterPropertiesOnDates(LocalDate fromDate, LocalDate toDate, Integer occupancy) {
        List<Long> ids = propertyRepository.findAllPropertiesIdByDatesAndOccupancy(fromDate, toDate, occupancy);
        System.out.println(ids);
        return propertyRepository.findAllByIdIn(ids).stream().map(PropertyResource::new).collect(Collectors.toList());
    }

}
