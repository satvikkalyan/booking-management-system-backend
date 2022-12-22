package com.se.bookingmanagementsystembackend.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.se.bookingmanagementsystembackend.business.resources.UserResource
import com.se.bookingmanagementsystembackend.exception.BookingManagementSystemBackendExceptionHandler
import com.se.bookingmanagementsystembackend.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class UserControllerSpec extends Specification {

    private static final String CONTROLLER_ENDPOINT = "/api/v1/users/"

    def userService = Mock UserService
    def objectMapper = new ObjectMapper()

    def sut = new UserController(userService)

    def mvc = MockMvcBuilders
            .standaloneSetup(sut)
            .setControllerAdvice(new BookingManagementSystemBackendExceptionHandler())
            .build()


    def "GET API - when get api with email is called, it should call service method only once"() {
        given:
        def userResource = new UserResource()

        when:
        def result = mvc
                .perform(get(CONTROLLER_ENDPOINT + "{email}/", "abc@gmail.com"))
                .andReturn()
                .response

        then:
        1 * userService.getUserDetails(_ as String) >> userResource
        result.status == HttpStatus.OK.value()
    }

    def "GET API - when get api with DOB is called, it should call service method only once and returns the age"() {
        given:
        String age = "24"

        when:
        def result = mvc
                .perform(get(CONTROLLER_ENDPOINT + "age/{dateOfBirth}/", LocalDate.parse("1997-08-23")))
                .andReturn()
                .response

        then:
        1 * userService.calculateAge(_ as LocalDate) >> age
        result.status == HttpStatus.OK.value()

    }

    def "GET API - when get api with mobile is called, it should call service method only once and retunrs userresource"() {
        given:
        def userResource = new UserResource()

        when:
        def result = mvc.perform(get(CONTROLLER_ENDPOINT + "mobile/{mobile}/", "8128128121"))
                .andReturn()
                .response

        then:
        1 * userService.getUserDetailsFromMobile(_ as String) >> userResource
        result.status == HttpStatus.OK.value()
    }

    def "POST-API - when post api is called, it saves new user"() {
        given:
        def userResource = new UserResource()
        userResource.setFirstName("ABC")
        userResource.setId(1L)

        when:
        def result = mvc.perform(post(CONTROLLER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userResource)))
                .andReturn().response

        then:
        1 * userService.saveUser(_ as UserResource) >> userResource
        result.status == HttpStatus.OK.value()
        noExceptionThrown()

    }
}
