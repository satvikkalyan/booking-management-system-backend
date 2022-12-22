package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.business.resources.SubscriptionResource;
import com.se.bookingmanagementsystembackend.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/subscriptions/")
public class SubscriptionController {

    private SubscriptionService subscriptionService;


    @PostMapping()
    public SubscriptionResource saveEmail(@Valid SubscriptionResource subscriptionResource){
        return subscriptionService.saveEmail(subscriptionResource);
    }

    @GetMapping()
    public List<SubscriptionResource> getAllSubscribedUsers(){
        return subscriptionService.getAllSubscribedUsers();
    }

    @DeleteMapping("delete/id/{id}")
    public void deleteSubscriptionById(@PathVariable Long id){
        subscriptionService.deleteSubscriptionById(id);
    }

    @DeleteMapping("delete/email/{email}")
    public void deleteSubscriptionByEmail(@PathVariable String email){
        subscriptionService.deleteSubscriptionByEmail(email);
    }
}
