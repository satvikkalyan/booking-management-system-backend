package com.se.bookingmanagementsystembackend.business.domain.subscription;

import com.se.bookingmanagementsystembackend.validator.BaseFieldRegex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = BaseFieldRegex.EMAIL_REGEX, message = "Invalid Email Format")
    private String email;


}
