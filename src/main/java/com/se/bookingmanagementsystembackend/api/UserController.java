package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.business.resources.UserResource;
import com.se.bookingmanagementsystembackend.service.UserService;
import com.se.bookingmanagementsystembackend.validator.BaseFieldRegex;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/users/")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public UserResource saveNewUser(@Valid @RequestBody UserResource userResource) {
        return userService.saveUser(userResource);
    }

    @GetMapping("{email}/")
    public UserResource getUserDetails(@Valid @PathVariable @Pattern(regexp = BaseFieldRegex.EMAIL_REGEX, message = "Invalid Email Format") String email) {
        return userService.getUserDetails(email);
    }

    @GetMapping("age/{dateOfBirth}/")
    public String getUserAge(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate dateOfBirth) {
        return userService.calculateAge(dateOfBirth);
    }

    @GetMapping("mobile/{mobile}/")
    public UserResource getUserDetailsFromMobile(@PathVariable String mobile) {
        return userService.getUserDetailsFromMobile(mobile);
    }

    @GetMapping()
    public List<UserResource> getAllRegisteredUsers(){
        return userService.getAllRegisteredUsers();
    }

    @DeleteMapping("delete/{id}/")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
