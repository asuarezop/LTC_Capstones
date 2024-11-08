package com.pluralsight.deli.models;

public class BagOfChips extends OrderItem implements Priceable {
    private String flavor;

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public void displayItem() {

    }
}
