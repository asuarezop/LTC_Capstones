package com.pluralsight.deli.models;

public abstract class Order implements Printable {
    private int orderId;
    private String customerName;
    private String customerEmail;
    private Sandwich customerSandwich;

    public abstract double getTotalCost();
    public abstract String getOrderDetails();

    @Override
    public void printTo() {
        System.out.println("print to receipt");
    }
}
