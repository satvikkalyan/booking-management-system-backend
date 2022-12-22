package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.business.resources.FeedbackResource;
import com.se.bookingmanagementsystembackend.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/feedbacks/")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping()
    public FeedbackResource saveNewFeedback(@Valid @RequestBody FeedbackResource feedbackResource){
        return feedbackService.saveNewFeedback(feedbackResource);
    }

    @GetMapping()
    public List<FeedbackResource> getAllFeedbacks(){
        return feedbackService.getAllFeedbacks();
    }
}
