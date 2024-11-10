package com.pluralsight.deli.models;

import com.pluralsight.deli.options.DrinkSize;

public class Drink extends OrderItem {
    private DrinkSize size;
    private String flavor;

    public Drink(DrinkSize size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public DrinkSize getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public String displayItem() {
        return String.format("%-10s, -%12s", size, flavor);
    }

    @Override
    public double getPrice() {
        //Using switch statement to return price of a Drink at different sizes
        return switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
            default -> 0.0;
        };
    }
}
