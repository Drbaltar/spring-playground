package com.drbaltar.springplayground.models;

public class Ticket {
    private final Person passenger;
    private final double price;

    public Ticket(Person passenger, double price) {
        this.passenger = passenger;
        this.price = price;

    }

    public Person getPassenger() {
        return passenger;
    }

    public double getPrice() {
        return price;
    }
}