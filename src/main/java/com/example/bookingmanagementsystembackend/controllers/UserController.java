package com.example.bookingmanagementsystembackend.controllers;


import com.example.bookingmanagementsystembackend.models.User;
import com.example.bookingmanagementsystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllMovies() {
        return new ResponseEntity<List<User>>(userService.allUsers(), HttpStatus.OK);
    }
}
