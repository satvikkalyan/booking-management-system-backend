package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.business.resources.PromotionResource;
import com.se.bookingmanagementsystembackend.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/promotions/")
@AllArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;


    @PostMapping()
    public void sendPromotionMessage(@Valid @RequestBody PromotionResource promotionResource) {
        promotionService.sendPromotionMessage(promotionResource);
    }

    @GetMapping()
    public List<PromotionResource> getAllPromotions() {
        return promotionService.getAllPromotions();
    }
}
