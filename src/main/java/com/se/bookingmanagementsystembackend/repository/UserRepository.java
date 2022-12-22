package com.se.bookingmanagementsystembackend.repository;

import com.se.bookingmanagementsystembackend.business.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByMobile(String mobile);

    @Query(value = "SELECT registered_date FROM user WHERE id = ?1", nativeQuery = true)
    LocalDate getRegisteredDate(Long id);


    @Query(value = "SELECT count(*) FROM user WHERE registered_date BETWEEN ?1 AND ?2 ",nativeQuery = true)
    Integer findUsersRegisteredBetween(LocalDate fromDate, LocalDate toDate);

    @Modifying
    @Query(value = "DELETE FROM user WHERE id = ?1",nativeQuery = true)
    void deleteUserById(Long id);
}
