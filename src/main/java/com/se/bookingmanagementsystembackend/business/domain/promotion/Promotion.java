package com.se.bookingmanagementsystembackend.business.domain.promotion;

import com.se.bookingmanagementsystembackend.business.resources.PromotionResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String promotionMessage;

    private LocalDate postedDate;

    private String promotionTitle;

    private float discount;

    public Promotion(PromotionResource promotionResource){
        this.id = promotionResource.getId();
        this.promotionMessage = promotionResource.getPromotionMessage();
        this.postedDate = promotionResource.getPostedDate();
        this.promotionTitle = promotionResource.getPromotionTitle();
        this.discount = promotionResource.getDiscount();
    }
}
