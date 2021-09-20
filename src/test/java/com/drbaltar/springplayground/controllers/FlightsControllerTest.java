package com.drbaltar.springplayground.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest (FlightsController.class)
public class FlightsControllerTest {
    @Autowired
    MockMvc mvc;

    @Disabled
    @Test
    public void testCreatPersonJSONClass() throws Exception{
        this.mvc.perform(
                        get("/flights/flight")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is(notNullValue())))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName", is(notNullValue())))
                .andExpect(jsonPath("$.tickets[0].price", is(notNullValue())))
                .andExpect(jsonPath("$.departs", is(notNullValue())));
    }

    @Disabled
    @Test
    public void testListOfFlights() throws Exception{
        this.mvc.perform(
                        get("/flights")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is(notNullValue())))
                .andExpect(jsonPath("$[1].tickets[0].price", is(notNullValue())));
    }

    @Test
    public void testCreatPersonJSONClassWithCaps() throws Exception{
        this.mvc.perform(
                        get("/flights/flight")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is(notNullValue())))
                .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is(notNullValue())))
                .andExpect(jsonPath("$.Tickets[0].Price", is(notNullValue())))
                .andExpect(jsonPath("$.Departs", is(notNullValue())));
    }

    @Test
    public void testListOfFlightsWithCaps() throws Exception{
        this.mvc.perform(
                        get("/flights")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is(notNullValue())))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger", not(hasProperty("LastName"))))
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(notNullValue())));

    }

}

