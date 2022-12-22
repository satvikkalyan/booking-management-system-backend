package com.se.bookingmanagementsystembackend.business.domain.feedback;

import com.se.bookingmanagementsystembackend.business.resources.FeedbackResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String mobile;

    private String feedback;

    public Feedback(FeedbackResource feedbackResource){
        this.id = feedbackResource.getId();
        this.name = feedbackResource.getName();
        this.email = feedbackResource.getEmail();
        this.mobile = feedbackResource.getMobile();
        this.feedback = feedbackResource.getFeedback();
    }

}
