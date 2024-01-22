package com.example.bookingmanagementsystembackend.service;

import com.example.bookingmanagementsystembackend.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookingmanagementsystembackend.models.Property;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> allProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyDetails(ObjectId id) {
        return propertyRepository.findById(id);
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public void deleteProperty(ObjectId id) {
        propertyRepository.deleteById(id);
    }
    public Optional<Property> updateProperty(ObjectId propertyId, Property updatedProperty) {
        Optional<Property> existingPropertyOptional = propertyRepository.findById(propertyId);
        if (existingPropertyOptional.isPresent()) {
            Property existingProperty = existingPropertyOptional.get();
            updateFields(existingProperty, updatedProperty);
            Property savedProperty = propertyRepository.save(existingProperty);
            return Optional.of(savedProperty);
        } else {
            return Optional.empty();
        }
    }

    private void updateFields(Property existingProperty, Property updatedProperty) {
        if (updatedProperty.getPropertyType() != null && !updatedProperty.getPropertyType().isEmpty()) {
            existingProperty.setPropertyType(updatedProperty.getPropertyType());
        }

        if (updatedProperty.getPropertyName() != null && !updatedProperty.getPropertyName().isEmpty()) {
            existingProperty.setPropertyName(updatedProperty.getPropertyName());
        }

        if (updatedProperty.getPropertyDescription() != null && !updatedProperty.getPropertyDescription().isEmpty()) {
            existingProperty.setPropertyDescription(updatedProperty.getPropertyDescription());
        }

        if (updatedProperty.getPropertyFacilities() != null && !updatedProperty.getPropertyFacilities().isEmpty()) {
            existingProperty.setPropertyFacilities(updatedProperty.getPropertyFacilities());
        }

        if (updatedProperty.getRating() > 0) {
            existingProperty.setRating(updatedProperty.getRating());
        }

        if (updatedProperty.getPrice() > 0) {
            existingProperty.setPrice(updatedProperty.getPrice());
        }

        if (updatedProperty.getDescription() != null && !updatedProperty.getDescription().isEmpty()) {
            existingProperty.setDescription(updatedProperty.getDescription());
        }

        if (updatedProperty.getStreet() != null && !updatedProperty.getStreet().isEmpty()) {
            existingProperty.setStreet(updatedProperty.getStreet());
        }

        if (updatedProperty.getShortTitle() != null && !updatedProperty.getShortTitle().isEmpty()) {
            existingProperty.setShortTitle(updatedProperty.getShortTitle());
        }

        if (updatedProperty.getShortDescription() != null && !updatedProperty.getShortDescription().isEmpty()) {
            existingProperty.setShortDescription(updatedProperty.getShortDescription());
        }

        if (updatedProperty.getRoom() != null) {
            existingProperty.setRoom(updatedProperty.getRoom());
        }

        if (updatedProperty.getCity() != null && !updatedProperty.getCity().isEmpty()) {
            existingProperty.setCity(updatedProperty.getCity());
        }

        if (updatedProperty.getImages() != null && !updatedProperty.getImages().isEmpty()) {
            existingProperty.setImages(updatedProperty.getImages());
        }
    }
}
