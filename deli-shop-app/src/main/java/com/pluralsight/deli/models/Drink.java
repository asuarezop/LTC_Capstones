package com.pluralsight.deli.models;

public class Drink extends OrderItem implements Priceable {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public double getPrice() {
        //Using switch statement to return price of a Drink at different sizes
        return switch (size) {
            case "SMALL" -> 2.00;
            case "MEDIUM" -> 2.50;
            case "LARGE" -> 3.00;
            default -> 0.0;
        };
    }

    @Override
    public void displayItem() {

    }
}
