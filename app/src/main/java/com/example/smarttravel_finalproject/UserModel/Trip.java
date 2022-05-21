package com.example.smarttravel_finalproject.UserModel;

public class Trip {
    String source;
    String destination;
    String price;
    String id;

    public Trip(String source, String destination, String price) {
        this.source = source;
        this.destination = destination;
        this.price = price;
    }
    public Trip(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
