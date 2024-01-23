package com.example.bookingmanagementsystembackend.controllers;


import com.example.bookingmanagementsystembackend.exceptions.UserAlreadyExistsException;
import com.example.bookingmanagementsystembackend.models.User;
import com.example.bookingmanagementsystembackend.models.UserLoginRequest;
import com.example.bookingmanagementsystembackend.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            User user = userService.loginUser(userLoginRequest.getEmail(), userLoginRequest.getPassword());
            return ResponseEntity.ok(user);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllMovies() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserDetails(@PathVariable ObjectId id){
        return new ResponseEntity<>(userService.getUserDetails(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User updatedUser) {
        Optional<User> updatedUserOptional = userService.updateUser(id, updatedUser);
        return updatedUserOptional.map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable ObjectId id){
        userService.deleteUser(id);
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user_) {
        try {
            User newUser = userService.registerUser(user_);
            return ResponseEntity.ok(newUser);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
