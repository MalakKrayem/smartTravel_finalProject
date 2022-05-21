package com.example.smarttravel_finalproject.AdminModel;

public class Bus {
    String source;
    String destination;
    String numOfSeats;
    String ticketPrice;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bus(String source, String destination, String numOfSeats, String ticketPrice) {
        this.source = source;
        this.destination = destination;
        this.numOfSeats = numOfSeats;
        this.ticketPrice = ticketPrice;
    }
    public Bus(){

    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(String numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
