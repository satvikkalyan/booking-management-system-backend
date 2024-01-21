package com.example.bookingmanagementsystembackend.service;


import com.example.bookingmanagementsystembackend.models.User;
import com.example.bookingmanagementsystembackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    // Inside Service class we write db access methods

    @Autowired
    private UserRepository userRepository;
    public List<User> allUsers(){
        return userRepository.findAll();
    }
}
