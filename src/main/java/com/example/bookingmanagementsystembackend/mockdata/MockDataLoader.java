package com.example.bookingmanagementsystembackend.mockdata;

import com.example.bookingmanagementsystembackend.models.Bookings;
import com.example.bookingmanagementsystembackend.models.User;
import com.example.bookingmanagementsystembackend.repository.UserRepository;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class MockDataLoader {
    private final UserRepository userRepository;
    public MockDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostConstruct
    public void loadData() {
        long userCount = userRepository.count();
        if (userCount == 0) {
            User newUser = new User();
            newUser.setFirstName("John");
            newUser.setLastName("Doe");
            newUser.setEmail("JohnDoe@email.com");
            newUser.setPassword("password");
            List<Bookings> bookings = new ArrayList<>();
            Bookings booking1 = new Bookings();
            bookings.add(booking1);
            newUser.setBookings(bookings);
            userRepository.save(newUser);
            System.out.println("Added Data");

        } else {
            System.out.println("Data Present Already");
        }
    }
}
