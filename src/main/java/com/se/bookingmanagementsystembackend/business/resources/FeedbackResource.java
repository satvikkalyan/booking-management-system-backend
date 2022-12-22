package com.se.bookingmanagementsystembackend.business.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.se.bookingmanagementsystembackend.business.domain.feedback.Feedback;
import com.se.bookingmanagementsystembackend.validator.BaseFieldRegex;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class FeedbackResource {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)    //feedback once submitted cannot be edited.
    private Long id;

    private String name;

    @Pattern(regexp = BaseFieldRegex.EMAIL_REGEX, message = "Invalid Email Format")
    private String email;

    private String mobile;

    private String feedback;

    public FeedbackResource(Feedback feedback){
        this.id = feedback.getId();
        this.email = feedback.getEmail();
        this.name = feedback.getName();
        this.mobile = feedback.getMobile();
        this.feedback = feedback.getFeedback();
    }
}
