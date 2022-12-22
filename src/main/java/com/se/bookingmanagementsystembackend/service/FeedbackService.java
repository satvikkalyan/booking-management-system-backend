package com.se.bookingmanagementsystembackend.service;

import com.se.bookingmanagementsystembackend.business.domain.feedback.Feedback;
import com.se.bookingmanagementsystembackend.business.resources.FeedbackResource;
import com.se.bookingmanagementsystembackend.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackResource saveNewFeedback(FeedbackResource feedbackResource) {
        return new FeedbackResource(feedbackRepository.save(new Feedback(feedbackResource)));
    }

    public List<FeedbackResource> getAllFeedbacks() {
        return feedbackRepository.findAll().stream().map(FeedbackResource::new).collect(Collectors.toList());

    }
}
