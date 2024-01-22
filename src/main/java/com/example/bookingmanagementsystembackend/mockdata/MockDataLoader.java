package com.example.bookingmanagementsystembackend.mockdata;

import com.example.bookingmanagementsystembackend.models.*;
import com.example.bookingmanagementsystembackend.repository.*;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MockDataLoader {
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final RoomRepository roomRepository;

    private final BookingsRepository bookingRepository;

    private final PaymentsRepository paymentsRepository;

    public MockDataLoader(UserRepository userRepository, PropertyRepository propertyRepository, RoomRepository roomRepository, BookingsRepository bookingRepository, PaymentsRepository paymentsRepository) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.paymentsRepository = paymentsRepository;
    }

    @PostConstruct
    public void loadData() {
        deleteAllData();
        loadUserData();
        loadRoomAndPropertyData();
        loadBookingAndPaymentData();
    }

    private void deleteAllData() {
        userRepository.deleteAll();
        propertyRepository.deleteAll();
        roomRepository.deleteAll();
        bookingRepository.deleteAll();
        paymentsRepository.deleteAll();
    }

    private void loadUserData() {
        long userCount = userRepository.count();
        if (userCount == 0) {
            User newUser = new User();
            newUser.setFirstName("John");
            newUser.setLastName("Doe");
            newUser.setEmail("JohnDoe@email.com");
            newUser.setPassword("password");
            userRepository.save(newUser);
            System.out.println("Added User Data");
        } else {
            System.out.println("User Data Present Already");
        }
    }

    private void loadRoomAndPropertyData() {
        long roomCount = roomRepository.count();
        long propertyCount = propertyRepository.count();

        if (roomCount == 0 && propertyCount == 0) {
            Room room1 = new Room(new ObjectId(), true, false, true, false);
            Room room2 = new Room(new ObjectId(), false, true, false, true);
            roomRepository.saveAll(List.of(room1, room2));
            System.out.println("Added Room Data");
            Property property1 = new Property(
                    new ObjectId(), "Apartment", "Cozy Apartment", "A comfortable apartment for your stay",
                    "Wi-Fi, Kitchen, Parking", 4, 100.0, "Enjoy your stay in this cozy apartment with modern amenities.",
                    "123 Main Street", "Cozy Apt", "Modern apartment with all facilities", room1, "Cityville",
                    List.of("image1.jpg", "image2.jpg")
            );
            Property property2 = new Property(
                    new ObjectId(), "House", "Spacious House", "A spacious house with a beautiful garden",
                    "Garden, Pool, Gym", 5, 200.0, "Experience luxury in this spacious house with stunning garden views.",
                    "456 Oak Avenue", "Luxury House", "Luxurious house with top-notch amenities", room2, "Luxurytown",
                    List.of("image3.jpg", "image4.jpg")
            );
            propertyRepository.saveAll(List.of(property1, property2));
            System.out.println("Added Property Data");

        } else {
            System.out.println("Room and Property Data Present Already");
        }
    }

    private void loadBookingAndPaymentData() {
        long bookingCount = bookingRepository.count();
        if (bookingCount == 0) {
            User user = userRepository.findAll().get(0);
            Property property = propertyRepository.findAll().get(0);
            Bookings booking1 = new Bookings(
                    new ObjectId(), property,user, null, new Date(), new Date(), List.of()
            );
            bookingRepository.saveAll(List.of(booking1));
            System.out.println("Added Booking Data");
            Payments payment1 = new Payments(
                    new ObjectId(), "PAID", 150.0F, "Credit Card"
            );
            booking1.setPayment(payment1);
            paymentsRepository.save(payment1);
            System.out.println("Added Payment Data");
            bookingRepository.save(booking1);
        } else {
            System.out.println("Booking and Payment Data Present Already");
        }
    }

}
