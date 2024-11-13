package com.pluralsight.deli.models;

import com.pluralsight.deli.options.ChipFlavor;

public class BagOfChips implements OrderItem {
    private ChipFlavor flavor;

    public BagOfChips(ChipFlavor flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String printToReceipt() {
        return String.format("Chips: %s $%.2f\n", flavor, getPrice());
    }

    @Override
    public String toString() {
        return String.format("Chips Flavor: %s", flavor);
    }
}
