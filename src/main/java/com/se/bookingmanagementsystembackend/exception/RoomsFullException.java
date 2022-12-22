package com.se.bookingmanagementsystembackend.exception;

import lombok.Getter;

@Getter
public class RoomsFullException extends RuntimeException{

    public RoomsFullException(String message){
        super(message);
    }
}
