package com.drbaltar.springplayground.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping("/flights")
public class FlightsController {

    @GetMapping("/flight")
    public Flight getFlight() {
        return getTestFlight();
    }

    @GetMapping
    public List<Flight> getFlights() {
        List<Flight> flights = new ArrayList<>();

        Person david = new Person();
        david.setFirstName("David");

        Calendar departs1 = Calendar.getInstance();
        departs1.set(2021, 8, 2);


        Flight testflight1 = new Flight(departs1);

        testflight1.addTicket(new Ticket(david, 200));
        flights.add(getTestFlight());
        flights.add(testflight1);

        return flights;
    }

    private Flight getTestFlight() {
        Person john = new Person();
        john.setFirstName("John");
        john.setLastName("Wilson");

        Calendar departs = Calendar.getInstance();
        departs.set(2021, 8, 1);


        Flight testflight = new Flight(departs);
        testflight.addTicket(new Ticket(john, 400));
        return testflight;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Person {
        private String firstName;
        private String lastName;

        @JsonProperty("FirstName")
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @JsonProperty("LastName")
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    static class Ticket {
        private final Person passenger;
        private final double price;

        public Ticket(Person passenger, double price) {
            this.passenger = passenger;
            this.price = price;

        }

        @JsonProperty("Passenger")
        public Person getPassenger() {
            return passenger;
        }

        @JsonProperty("Price")
        public double getPrice() {
            return price;
        }
    }

    static class Flight {
        ArrayList<Ticket> tickets;
        Calendar departs;

        public Flight(Calendar departs) {
            this.departs = departs;
            tickets = new ArrayList<>();

        }

        @JsonProperty("Tickets")
        public ArrayList<Ticket> getTickets() {
            return tickets;
        }

        public void addTicket(Ticket ticket) {
            tickets.add(ticket);
        }

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Chicago")
        @JsonProperty("Departs")
        public Calendar getDeparts() {
            return departs;
        }
    }
}
