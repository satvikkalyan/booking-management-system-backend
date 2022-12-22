package com.se.bookingmanagementsystembackend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyCheckoutException extends RuntimeException{

    public UserAlreadyCheckoutException(String message){
        super(message);
    }
}
