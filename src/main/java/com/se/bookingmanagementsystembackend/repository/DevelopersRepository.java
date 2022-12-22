package com.se.bookingmanagementsystembackend.repository;

import com.se.bookingmanagementsystembackend.business.domain.developers.Developers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DevelopersRepository extends JpaRepository<Developers,Long> {

    Optional<Developers> findByName(String name);
}
