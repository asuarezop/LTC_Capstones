package com.pluralsight.deli.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private final List<OrderItem> orderItems = new ArrayList<>();

    //Default constructor
    public Order() {}

    public Order(int orderId, LocalDate orderDate, LocalTime orderTime) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    //Adds a new order item
    public void addToOrder(OrderItem o) {
        orderItems.add(o);
    }

    //Calculate total cost of order
    public double getTotalCost(List<OrderItem> items) {
        double orderTotal = 0.0;

        for (OrderItem o: items) {
            //Adding all order items prices
            orderTotal += o.getPrice();
        }

        return orderTotal;
    };

    public void printToReceipt() {
        System.out.println("print to receipt");
    }
}
