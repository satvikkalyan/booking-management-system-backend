package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/user/login/")
public class LoginController {
    private final UserService userService;

    @PostMapping("{email}/{password}/")
    public Boolean loginUserToSystem(@Valid @PathVariable String email,
                                          @Valid @PathVariable String password){
    return userService.checkLoginCredentials(email,password);

    }
}
