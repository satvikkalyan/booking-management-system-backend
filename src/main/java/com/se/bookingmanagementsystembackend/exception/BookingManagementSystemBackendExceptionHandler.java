package com.se.bookingmanagementsystembackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Slf4j
public class BookingManagementSystemBackendExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistsException e) {
        Map<String, String> mapException = new HashMap<>();
        mapException.put(e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST, e.getClass().getSimpleName(), mapException), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomsFullException.class)
    public ResponseEntity<ErrorResponse> handleRoomsFullException(RoomsFullException e) {
        Map<String, String> mapException = new HashMap<>();
        mapException.put(e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                e.getClass().getSimpleName(),
                mapException), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyCheckoutException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyCheckoutException(UserAlreadyCheckoutException e){
        Map<String,String> mapException = new HashMap<>();
        mapException.put(e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                e.getClass().getSimpleName(),
                mapException), HttpStatus.BAD_REQUEST);
    }
}
