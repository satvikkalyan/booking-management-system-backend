package com.example.bookingmanagementsystembackend.service;


import com.example.bookingmanagementsystembackend.exceptions.UserAlreadyExistsException;
import com.example.bookingmanagementsystembackend.models.User;
import com.example.bookingmanagementsystembackend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // Inside Service class we write db access methods
    // Most Business Logic Goes Here
    @Autowired
    private UserRepository userRepository;
    public List<User> allUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserDetails(ObjectId id){
        return userRepository.findById(id);
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }

    public User loginUser(String email, String password) throws AuthenticationException {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new AuthenticationException("Invalid email or password.");
        }
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists.");
        }
        return userRepository.save(user);
    }
    public Optional<User> updateUser(ObjectId userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            String firstName = updatedUser.getFirstName();
            String lastName = updatedUser.getLastName();
            String email = updatedUser.getEmail();
            String password = updatedUser.getPassword();
            if (firstName != null && !firstName.isEmpty()) {
                existingUser.setFirstName(firstName);
            }

            if (lastName != null && !lastName.isEmpty()) {
                existingUser.setLastName(lastName);
            }

            if (email != null && !email.isEmpty()) {
                existingUser.setEmail(email);
            }

            if (password != null && !password.isEmpty()) {
                existingUser.setPassword(password);
            }

            User savedUser = userRepository.save(existingUser);
            return Optional.of(savedUser);
        } else {
            return Optional.empty();
        }
    }
}
