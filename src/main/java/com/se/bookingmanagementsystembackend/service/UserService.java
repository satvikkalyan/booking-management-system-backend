package com.se.bookingmanagementsystembackend.service;

import com.se.bookingmanagementsystembackend.business.domain.user.User;
import com.se.bookingmanagementsystembackend.business.resources.UserResource;
import com.se.bookingmanagementsystembackend.exception.UserAlreadyExistsException;
import com.se.bookingmanagementsystembackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private boolean checkIfUserAlreadyExists(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent();
    }

    @Transactional
    public UserResource saveUser(UserResource userResource) {
        if (userResource.getId() == null && checkIfUserAlreadyExists(userResource.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        } else if (userResource.getId() != null && checkIfUserAlreadyExists(userResource.getEmail())) {
            User newUser = new User(userResource);
            newUser.setRegisteredDate(getLastRegisteredDate(userResource.getId()));
            userRepository.save(newUser);
            return new UserResource(newUser);
        } else {
            User newUser = new User(userResource);
            newUser.setRegisteredDate(LocalDate.now());
            userRepository.save(newUser);
            return new UserResource(newUser);
        }
    }

    public UserResource getUserDetails(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            System.out.println(userOptional.get());
            return new UserResource(userOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public boolean checkLoginCredentials(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);
        if (userOptional.isPresent()) {
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email doesn't exist or password incorrect");
        }
    }

    public String calculateAge(LocalDate dateOfBirth) {
        return String.valueOf(LocalDate.now().getYear() - dateOfBirth.getYear());
    }

    public UserResource getUserDetailsFromMobile(String mobile) {
        Optional<User> userOptional = userRepository.findByMobile(mobile);
        if (userOptional.isPresent()) {
            return new UserResource(userOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User details not found for given mobile no");
        }
    }

    public LocalDate getLastRegisteredDate(Long id){
        return userRepository.getRegisteredDate(id);
    }

    public List<UserResource> getAllRegisteredUsers(){
        return userRepository.findAll().stream().map(UserResource::new).collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserById(Long id){
        userRepository.deleteUserById(id);
    }
}
