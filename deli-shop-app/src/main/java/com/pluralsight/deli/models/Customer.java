package com.pluralsight.deli.models;

import com.pluralsight.deli.options.PaymentOption;

import java.util.List;

public class Customer {
    private String name;
    private List<Order> orders;
    private PaymentOption payment;

    public Customer() {}

    public double payFor(Order o) {
        return 0.0;
    }

    public void addTip(double tip) {}
}
