package com.se.bookingmanagementsystembackend.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequestMapping("/api/v1/profileimage/")
public class ImageController {

    @PostMapping()
    public void uploadImage(MultipartFile image){
        System.out.println(image);

    }
}
