package com.example.bookingmanagementsystembackend.mockdata;

import com.example.bookingmanagementsystembackend.models.*;
import com.example.bookingmanagementsystembackend.repository.*;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;

@Component
public class MockDataLoader {
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final RoomAvailabilityRepository roomAvailabilityRepository;

    private final BookingsRepository bookingRepository;

    private final PaymentsRepository paymentsRepository;

    public MockDataLoader(UserRepository userRepository, PropertyRepository propertyRepository, RoomAvailabilityRepository roomAvailabilityRepository, BookingsRepository bookingRepository, PaymentsRepository paymentsRepository) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.roomAvailabilityRepository = roomAvailabilityRepository;
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
        roomAvailabilityRepository.deleteAll();
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
        long propertyCount = propertyRepository.count();

        if (propertyCount == 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            // Construct RoomAvailability objects with NextAvailability dates
            RoomAvailability roomAvailability1NorthEast = new RoomAvailability(new ObjectId(), "north-east", LocalDate.parse("01/20/2025", formatter));
            RoomAvailability roomAvailability1SouthEast = new RoomAvailability(new ObjectId(), "south-east", LocalDate.parse("01/22/2022", formatter) );
            RoomAvailability roomAvailability1NorthWest = new RoomAvailability(new ObjectId(), "north-west", LocalDate.parse("01/20/2022", formatter));
            RoomAvailability roomAvailability1SouthWest = new RoomAvailability(new ObjectId(), "south-west", LocalDate.parse("01/22/2022", formatter) );

            RoomAvailability roomAvailability2NorthEast = new RoomAvailability(new ObjectId(), "north-east", LocalDate.parse("01/20/2025", formatter));
            RoomAvailability roomAvailability2SouthEast = new RoomAvailability(new ObjectId(), "south-east", LocalDate.parse("01/22/2022", formatter) );
            RoomAvailability roomAvailability2NorthWest = new RoomAvailability(new ObjectId(), "north-west", LocalDate.parse("01/20/2022", formatter));
            RoomAvailability roomAvailability2SouthWest = new RoomAvailability(new ObjectId(), "south-west", LocalDate.parse("01/22/2022", formatter) );


            // Construct Property objects with associated RoomAvailability
            Property property1 = new Property(
                    new ObjectId(), "Apartment", "Cozy Apartment", "A comfortable apartment for your stay",
                    "Wi-Fi, Kitchen, Parking", 4, 100.0, "Enjoy your stay in this cozy apartment with modern amenities.",
                    "123 Main Street", "Cozy Apt", "Modern apartment with all facilities",
                    List.of(roomAvailability1NorthEast, roomAvailability1SouthEast,roomAvailability1NorthWest, roomAvailability1SouthWest),
                    "Cityville",
                    "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                    List.of("https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                            "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                            "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                            "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                            "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                            "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1"
                            )
            );
            roomAvailability1NorthEast.setPropertyId(property1.getPropertyId());
            roomAvailability1SouthEast.setPropertyId(property1.getPropertyId());
            roomAvailability1NorthWest.setPropertyId(property1.getPropertyId());
            roomAvailability1SouthWest.setPropertyId(property1.getPropertyId());
            Property property2 = new Property(
                    new ObjectId(), "House", "Spacious House", "A spacious house with a beautiful garden",
                    "Garden, Pool, Gym", 5, 200.0, "Experience luxury in this spacious house with stunning garden views.",
                    "456 Oak Avenue", "Luxury House", "Luxurious house with top-notch amenities",
                    List.of(roomAvailability2NorthWest,roomAvailability2NorthEast, roomAvailability2SouthWest,roomAvailability2SouthEast),
                    "Cityville", "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",                    List.of("https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                    "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                    "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                    "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                    "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1",
                    "https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1"

            )
            );
            roomAvailability2NorthWest.setPropertyId(property2.getPropertyId());
            roomAvailability2NorthEast.setPropertyId(property2.getPropertyId());
            roomAvailability2SouthWest.setPropertyId(property2.getPropertyId());
            roomAvailability2SouthEast.setPropertyId(property2.getPropertyId());
            propertyRepository.saveAll(List.of(property1, property2));
            roomAvailabilityRepository.saveAll(List.of(roomAvailability1NorthEast, roomAvailability1SouthEast,roomAvailability1NorthWest, roomAvailability1SouthWest,roomAvailability2NorthWest,roomAvailability2NorthEast, roomAvailability2SouthWest,roomAvailability2SouthEast));
            System.out.println("Added Property Data");
        } else {
            System.out.println("Property Data Present Already");
        }
    }
    private void loadBookingAndPaymentData() {
        long bookingCount = bookingRepository.count();
        if (bookingCount == 0) {
            User user = userRepository.findAll().get(0);
            Property property = propertyRepository.findAll().get(0);
            Bookings booking1 = new Bookings(
                    new ObjectId(), property,user, null, new Date(), new Date(), ""
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
