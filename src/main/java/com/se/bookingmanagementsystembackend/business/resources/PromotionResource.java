package com.se.bookingmanagementsystembackend.business.resources;

import com.se.bookingmanagementsystembackend.business.domain.promotion.Promotion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PromotionResource {

    private Long id;

    private String promotionMessage;

    private LocalDate postedDate;

    private String promotionTitle;

    private float discount;

    public PromotionResource(Promotion promotion){
        this.id = promotion.getId();
        this.promotionMessage = promotion.getPromotionMessage();
        this.postedDate = promotion.getPostedDate();
        this.promotionTitle = promotion.getPromotionTitle();
        this.discount = promotion.getDiscount();
    }

}
