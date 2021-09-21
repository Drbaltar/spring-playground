package com.drbaltar.springplayground.controllers;

import com.drbaltar.springplayground.models.Flight;
import com.drbaltar.springplayground.models.Person;
import com.drbaltar.springplayground.models.Ticket;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/flights")
public class FlightsController {

    @PostMapping("/tickets/total")
    public Map<String, Integer> getTicketTotal(@RequestBody FlightWithoutDeparts flight) {
        var total = 0;
        for (Ticket ticket : flight.tickets) {
            total += ticket.getPrice();
        }

        Map<String, Integer> results = new HashMap<>();
        results.put("result", total);
        return results;
    }

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
        departs1.set(2021, Calendar.AUGUST, 2);


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
        departs.set(2021, Calendar.AUGUST, 1);


        Flight testflight = new Flight(departs);
        testflight.addTicket(new Ticket(john, 400));
        return testflight;
    }

    record FlightWithoutDeparts(ArrayList<Ticket> tickets) {
    }

    @PostMapping("/totals")
    public Map<String, Integer> getTotalCostFlights(@RequestBody ArrayList<FlightWithoutDeparts> flights){
        Map<String, Integer> grandTotal = new HashMap<>();
        int total = 0;
        for (FlightWithoutDeparts flight : flights) {
            for (Ticket ticket : flight.tickets) {
                total += ticket.getPrice();
            }
        }

        grandTotal.put("result", total);
        return grandTotal;
    }
}
