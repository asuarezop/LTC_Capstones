package com.pluralsight.deli.models;

import com.pluralsight.deli.options.ChipFlavor;

public class BagOfChips extends OrderItem {
    private ChipFlavor flavor;

    public BagOfChips(ChipFlavor flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String displayItem() {
        return String.format("%-10s", flavor);
    }
}
