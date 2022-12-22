package com.se.bookingmanagementsystembackend.business.domain.user;

import com.se.bookingmanagementsystembackend.business.domain.booking.Booking;
import com.se.bookingmanagementsystembackend.business.domain.card.CardDetails;
import com.se.bookingmanagementsystembackend.business.resources.UserResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
    @Column(unique = true)
    private String mobile;

    private boolean isCustomer;

    private boolean isManagement;

    private boolean isOnSiteEmployee;

    private boolean isCustomerSupportTeam;

    private String address;

    private String profileImageUrl;


    private String userRating;

    private LocalDate dateOfBirth;

    private String gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CardDetails> cardDetailsList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Booking> bookings;

    private LocalDate registeredDate;


    public User(UserResource userResource) {
        this.id = userResource.getId();
        this.firstName = userResource.getFirstName();
        this.lastName = userResource.getLastName();
        this.email = userResource.getEmail();
        this.password = userResource.getPassword();
        this.mobile = userResource.getMobile();
        this.isCustomer = userResource.isCustomer();
        this.isManagement = userResource.isManagement();
        this.isOnSiteEmployee = userResource.isOnSiteEmployee();
        this.isCustomerSupportTeam = userResource.isCustomerSupportTeam();
        this.address = userResource.getAddress();
        this.profileImageUrl = userResource.getProfileImageUrl();
        this.dateOfBirth = userResource.getDateOfBirth();
        this.gender = userResource.getGender();
        this.cardDetailsList = userResource.getCardDetailsResourceList()
                .stream().map(cardDetailsResource -> new CardDetails(cardDetailsResource, this)).collect(Collectors.toList());
        this.bookings = userResource.getBookingResources()
                .stream().map(bookingResource -> new Booking(bookingResource, this, null,null)).collect(Collectors.toList());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
