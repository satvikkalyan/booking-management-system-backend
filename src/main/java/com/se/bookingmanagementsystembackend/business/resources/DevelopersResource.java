package com.se.bookingmanagementsystembackend.business.resources;

import com.se.bookingmanagementsystembackend.business.domain.developers.Developers;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DevelopersResource {

    private Long id;

    private String name;

    private String lastName;

    public DevelopersResource(Developers developers) {
        this.id = developers.getId();
        this.name = developers.getName();
        this.lastName = developers.getLastName();
    }
}
