package com.se.bookingmanagementsystembackend.business.domain.card;

import com.se.bookingmanagementsystembackend.business.domain.user.User;
import com.se.bookingmanagementsystembackend.business.resources.CardDetailsResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    private Long cardNumber;

    private String cardName;

    //Will be stored as mm/yy
    private String cardExpiry;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private User user;

    public CardDetails(CardDetailsResource cardDetailsResource, User user) {
        this.id = cardDetailsResource.getId();
        this.cardNumber = cardDetailsResource.getCardNumber();
        this.cardName = cardDetailsResource.getCardName();
        this.cardExpiry = cardDetailsResource.getCardExpiry();
        this.user = user;
    }


}
