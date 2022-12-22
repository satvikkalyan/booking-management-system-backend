package com.se.bookingmanagementsystembackend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TestController {

    @GetMapping()
    public String getName(){
        return "SE Team 2. Indiana University Bloomington";
    }
}
