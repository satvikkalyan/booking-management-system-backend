package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.business.resources.DevelopersResource;
import com.se.bookingmanagementsystembackend.service.DevelopersService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/developers/")
public class DevelopersController {

    private final DevelopersService developersService;

    @GetMapping("{name}/")
    public DevelopersResource getDeveloperDetails(@PathVariable String name){
        return developersService.getDeveloperDetails(name);
    }
}
