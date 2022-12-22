package com.se.bookingmanagementsystembackend.business.resources;

import com.se.bookingmanagementsystembackend.business.domain.subscription.Subscription;
import com.se.bookingmanagementsystembackend.validator.BaseFieldRegex;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class SubscriptionResource {

    private Long id;

    @Pattern(regexp = BaseFieldRegex.EMAIL_REGEX, message = "Invalid Email Format")
    private String email;


    public SubscriptionResource(Subscription subscription) {
        this.id = subscription.getId();
        this.email = subscription.getEmail();
    }
}
