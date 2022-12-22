package com.se.bookingmanagementsystembackend.business.resources;


import com.se.bookingmanagementsystembackend.business.domain.card.CardDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardDetailsResource {

    private Long id;

    private Long cardNumber;

    private String cardName;

    private String cardExpiry;


    public CardDetailsResource(CardDetails cardDetails) {
        this.id = cardDetails.getId();
        this.cardNumber = cardDetails.getCardNumber();
        this.cardName = cardDetails.getCardName();
        this.cardExpiry = cardDetails.getCardExpiry();
    }

}
