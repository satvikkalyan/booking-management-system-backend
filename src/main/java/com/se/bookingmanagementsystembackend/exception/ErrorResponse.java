package com.se.bookingmanagementsystembackend.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timeStamp;

    private HttpStatus code;

    private String exception;

    private Map<String,String> errors;
}
