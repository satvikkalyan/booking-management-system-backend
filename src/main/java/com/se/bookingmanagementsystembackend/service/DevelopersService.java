package com.se.bookingmanagementsystembackend.service;

import com.se.bookingmanagementsystembackend.business.domain.developers.Developers;
import com.se.bookingmanagementsystembackend.business.resources.DevelopersResource;
import com.se.bookingmanagementsystembackend.repository.DevelopersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class DevelopersService {

    private final DevelopersRepository developersRepository;


    public DevelopersResource getDeveloperDetails(String name){
        Optional<Developers> developerOptional = developersRepository.findByName(name);
        if (!developerOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Developer not found");
        }
        return new DevelopersResource(developerOptional.get());
    }
}
