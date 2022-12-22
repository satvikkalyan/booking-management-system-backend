package com.se.bookingmanagementsystembackend.service;

import com.se.bookingmanagementsystembackend.business.domain.promotion.Promotion;
import com.se.bookingmanagementsystembackend.business.resources.PromotionResource;
import com.se.bookingmanagementsystembackend.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final EmailService emailService;

    public void sendPromotionMessage(PromotionResource promotionResource) {
        promotionResource.setPostedDate(LocalDate.now());
        promotionRepository.save(new Promotion(promotionResource));
        emailService.sendPromotionEmail(promotionResource);
    }

    public List<PromotionResource> getAllPromotions(){
        return promotionRepository.findAll().stream().map(PromotionResource::new).collect(Collectors.toList());
    }
}
