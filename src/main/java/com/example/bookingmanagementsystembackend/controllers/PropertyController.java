package com.example.bookingmanagementsystembackend.controllers;

import com.example.bookingmanagementsystembackend.models.Property;
import com.example.bookingmanagementsystembackend.models.datatransferobjects.PropertySearchRequest;
import com.example.bookingmanagementsystembackend.service.PropertyService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.allProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Property>> getPropertyDetails(@PathVariable ObjectId id) {
        Optional<Property> propertyDetails = propertyService.getPropertyDetails(id);
        return new ResponseEntity<>(propertyDetails, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property createdProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable ObjectId id, @RequestBody Property updatedProperty) {
        Optional<Property> updatedPropertyOptional = propertyService.updateProperty(id, updatedProperty);
        return updatedPropertyOptional.map(property -> ResponseEntity.ok().body(property))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable ObjectId id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/search")
    public ResponseEntity<List<Property>> searchProperties(@RequestBody PropertySearchRequest request) {
        try {
            if (request.getDestination() != null && !request.getDestination().matches("^[a-zA-Z0-9 ]+$")) {
                return ResponseEntity.badRequest().body(null);
            }

            // Validate date format (MM/dd/yyyy)
            if (!isValidDateFormat(request.getStartDate()) || !isValidDateFormat(request.getEndDate())) {
                return ResponseEntity.badRequest().body(null);
            }

            // Validate number of beds/adults is a positive natural number
            if (request.getNumberOfBeds() <= 0) {
                return ResponseEntity.badRequest().body(null);
            }

            List<Property> properties = propertyService.searchProperties(request);
            System.out.println(properties.isEmpty());
            return ResponseEntity.ok(properties);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    private boolean isValidDateFormat(String date) {
        // Regex for MM/dd/yyyy format
        String dateFormatRegex = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$";
        boolean isValid = date.matches(dateFormatRegex);
        if (!isValid) {
            System.out.println("Invalid date format: " + date);
        }
        return isValid;
    }
}
