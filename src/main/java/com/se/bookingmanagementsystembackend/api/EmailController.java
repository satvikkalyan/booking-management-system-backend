package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/sendemail/")
public class EmailController {

    private final EmailService emailService;

    @PostMapping()
    @Deprecated
    public void sendEmail(){
        emailService.sendEmail();
    }
}
