package com.se.bookingmanagementsystembackend.business.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.se.bookingmanagementsystembackend.business.domain.user.User;
import com.se.bookingmanagementsystembackend.validator.BaseFieldRegex;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserResource {
    private Long id;

    private String firstName;

    private String lastName;

    @Pattern(regexp = BaseFieldRegex.EMAIL_REGEX, message = "Invalid Email Format")
    private String email;

    private String password;

    @Size(min = 10, max = 10)
    @Column(unique = true)
    private String mobile;

    private boolean isCustomer;

    private boolean isManagement;

    private boolean isOnSiteEmployee;

    private boolean isCustomerSupportTeam;

    private String address;

    private String profileImageUrl;

    private String userRating;

    private String gender;

    private LocalDate dateOfBirth;

    private List<CardDetailsResource> cardDetailsResourceList;

    private List<BookingResource> bookingResources;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate registeredDate;


    public UserResource(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.mobile = user.getMobile();
        this.isCustomer = user.isCustomer();
        this.isManagement = user.isManagement();
        this.isOnSiteEmployee = user.isOnSiteEmployee();
        this.isCustomerSupportTeam = user.isCustomerSupportTeam();
        this.address = user.getAddress();
        this.profileImageUrl = user.getProfileImageUrl();
        this.dateOfBirth = user.getDateOfBirth();
        this.gender = user.getGender();
        this.cardDetailsResourceList = user.getCardDetailsList().size() > 0 ? user.getCardDetailsList().stream().map(CardDetailsResource::new).collect(Collectors.toList()) : null;
        this.bookingResources = user.getBookings().size() > 0 ? user.getBookings().stream().map(BookingResource::new).collect(Collectors.toList()) : null;
        this.registeredDate = user.getRegisteredDate();
    }
}
