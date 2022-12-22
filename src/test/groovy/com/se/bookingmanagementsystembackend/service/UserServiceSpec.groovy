package com.se.bookingmanagementsystembackend.service


import com.se.bookingmanagementsystembackend.business.domain.user.User
import com.se.bookingmanagementsystembackend.business.resources.BookingResource
import com.se.bookingmanagementsystembackend.business.resources.CardDetailsResource
import com.se.bookingmanagementsystembackend.business.resources.UserResource
import com.se.bookingmanagementsystembackend.repository.UserRepository
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

import java.time.LocalDate

class UserServiceSpec extends Specification {

    def userRepository = Mock UserRepository

    def userService = new UserService(userRepository)

    def "getUserDetails - should respond with empty details as no record is found"() {

        when:
        userService.getUserDetails(_ as String)

        then:
        1 * userRepository.findByEmail(_ as String) >> Optional.empty()
        thrown ResponseStatusException

    }

    def "getUserDetails - should fetch user details from email"() {
        given:
        def cardDetailsResource = new CardDetailsResource(id: 1L, cardNumber: 123123)
        List<CardDetailsResource> cardDetailsResourceList = new ArrayList<>()
        cardDetailsResourceList.add(cardDetailsResource)

        def bookingResource = new BookingResource(id: 1L, fromDate: LocalDate.parse("2022-11-20"), toDate: LocalDate.parse("2022-11-25"))
        List<BookingResource> bookingResourceList = new ArrayList<>()
        bookingResourceList.add(bookingResource)

        def userResource = new UserResource(id: 1L, email: "abc@gmail.com", firstName: "Temp", isCustomer: Boolean.TRUE, cardDetailsResourceList: cardDetailsResourceList, bookingResources: bookingResourceList)

        when:
        userService.getUserDetails(_ as String)

        then:
        1 * userRepository.findByEmail(_ as String) >> Optional.of(new User(userResource))


    }
}
