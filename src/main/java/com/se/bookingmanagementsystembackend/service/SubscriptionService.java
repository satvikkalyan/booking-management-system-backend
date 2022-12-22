package com.se.bookingmanagementsystembackend.service;

import com.se.bookingmanagementsystembackend.business.domain.subscription.Subscription;
import com.se.bookingmanagementsystembackend.business.resources.SubscriptionResource;
import com.se.bookingmanagementsystembackend.exception.UserAlreadyExistsException;
import com.se.bookingmanagementsystembackend.repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    public SubscriptionResource saveEmail(SubscriptionResource subscriptionResource){
        var optionalEmail = subscriptionRepository.findByEmail(subscriptionResource.getEmail());

        if (optionalEmail.isPresent()){
            throw new UserAlreadyExistsException("The email is already subscribed to our mailing list.");
        } else{
            Subscription newEntry = new Subscription();
            newEntry.setEmail(subscriptionResource.getEmail());
            subscriptionRepository.save(newEntry);
            return new SubscriptionResource(newEntry);
        }
    }

    public List<SubscriptionResource> getAllSubscribedUsers(){
        return subscriptionRepository.findAll().stream().map(SubscriptionResource::new).collect(Collectors.toList());
    }

    @Transactional
    public void deleteSubscriptionById(Long id){
        subscriptionRepository.deleteById(id);
    }

    @Transactional
    public void deleteSubscriptionByEmail(String email){
        subscriptionRepository.deleteByEmail(email);
    }
}
