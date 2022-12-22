package com.se.bookingmanagementsystembackend.business.domain.developers;

import com.se.bookingmanagementsystembackend.business.resources.DevelopersResource;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Developers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;


    public Developers(DevelopersResource developersResource) {
        this.id = developersResource.getId();
        this.name = developersResource.getName();
        this.lastName = developersResource.getLastName();
    }
}
