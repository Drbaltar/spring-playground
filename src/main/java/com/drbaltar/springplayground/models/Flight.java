package com.drbaltar.springplayground.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Calendar;

public class Flight {
    ArrayList<Ticket> tickets;
    Calendar departs;

    public Flight(Calendar departs) {
        this.departs = departs;
        tickets = new ArrayList<>();

    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Chicago")
    public Calendar getDeparts() {
        return departs;
    }
}