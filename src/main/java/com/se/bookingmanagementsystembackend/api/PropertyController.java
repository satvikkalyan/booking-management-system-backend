package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.business.resources.PropertyResource;
import com.se.bookingmanagementsystembackend.business.resources.RoomTypeResource;
import com.se.bookingmanagementsystembackend.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/properties/")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping()
    public List<PropertyResource> getAllHotels() {
        return propertyService.fetchAllHotels();
    }

    @GetMapping("city/{city}/")
    public List<PropertyResource> getAllHotelsByCity(@PathVariable String city) {
        return propertyService.fetchHotelsByCity(city);
    }

    @PostMapping()
    public PropertyResource saveNewHotel(@Valid @RequestBody PropertyResource propertyResource) {
        return propertyService.saveNewHotel(propertyResource);
    }

    @GetMapping("{id}/")
    public PropertyResource getHotelById(@PathVariable Long id) {
        return propertyService.getHotelById(id);
    }

    @DeleteMapping("delete/{id}/")
    public void deleteHotelByID(@PathVariable Long id) {
        propertyService.deleteHotelById(id);
    }

    @GetMapping("citycount/")
    public List<String> getCountByCities() {
        return propertyService.getCountByCities();
    }

    @GetMapping("typecount/")
    public List<String> getCountByTypes() {
        return propertyService.getCountByTypes();
    }

    @GetMapping("rooms/{id}/")
    @Operation(summary = "Get all rooms available for given property")
    public List<RoomTypeResource> getAllRoomsForPropertyById(@PathVariable Long id) {
        return propertyService.getAllRoomsForPropertyById(id);
    }

    @GetMapping("filter/{fromDate}/{toDate}/{count}")
    public List<PropertyResource> filterPropertiesOnDates(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate fromDate,
                                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate toDate,
                                                          @PathVariable Integer count) {
        return propertyService.filterPropertiesOnDates(fromDate, toDate, count);
    }
}