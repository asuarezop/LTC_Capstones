package com.pluralsight.easyshop.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private LocalDateTime date;
    private String address;
    private String city;
    private String state;
    private String zip;
    private BigDecimal shippingAmount;
    private List<OrderLineItem> lineItems = new ArrayList<>();

    public Order() {
    }

    public Order(int orderId, int userId, LocalDateTime date, String address, String city, String state, String zip, BigDecimal shippingAmount, List<OrderLineItem> lineItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.shippingAmount = shippingAmount;
        this.lineItems = lineItems;
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

    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public void add(OrderLineItem item) {
        lineItems.add(item);
    }
}
