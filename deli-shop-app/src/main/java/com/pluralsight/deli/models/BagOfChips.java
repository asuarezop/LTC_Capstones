package com.pluralsight.deli.models;

import com.pluralsight.deli.enums.ChipFlavor;
import com.pluralsight.deli.interfaces.OrderItem;

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
        return String.format("Chips: %-32s $%.2f\n", flavor, getPrice());
    }

    @Override
    public String toString() {
        return String.format("Chips Flavor: %s", flavor);
    }
}
