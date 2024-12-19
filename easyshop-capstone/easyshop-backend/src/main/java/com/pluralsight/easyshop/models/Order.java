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
    private BigDecimal orderTotal;

    public Order(int orderId, int userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public Order(int orderId, int userId, LocalDateTime date, String address, String city, String state, String zip, BigDecimal shippingAmount, List<OrderLineItem> lineItems, BigDecimal orderTotal) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.shippingAmount = shippingAmount;
        this.lineItems = lineItems;
        this.orderTotal = orderTotal;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
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

    public BigDecimal getOrderTotal() {
        return lineItems.stream().map(OrderLineItem::getOrderLineTotal).reduce(BigDecimal::add).orElse(null);
    }

}
