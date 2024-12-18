package com.pluralsight.easyshop.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private LocalDateTime date;
    private String address;
    private String city;
    private String state;
    private String zip;
    private double shipping_amount;
    private List<OrderLineItem> lineItems = new ArrayList<>();

    public Order() {
    }

    public Order(int orderId, LocalDateTime date, String address, String city, String state, String zip, double shipping_amount) {
        this.orderId = orderId;
        this.date = date;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.shipping_amount = shipping_amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public double getShipping_amount() {
        return shipping_amount;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }
}
