package com.pluralsight.deli.models;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Order implements Priceable {
    private int orderId;
    private LocalDate orderDate;
    private LocalTime orderTime;

    public abstract double getTotalCost();
    public abstract String getOrderDetails();

//    @Override
//    public void printTo() {
//        System.out.println("print to receipt");
//    }
}
