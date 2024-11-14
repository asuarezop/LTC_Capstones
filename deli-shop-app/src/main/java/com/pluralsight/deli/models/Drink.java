package com.pluralsight.deli.models;

import com.pluralsight.deli.enums.DrinkFlavor;
import com.pluralsight.deli.enums.DrinkSize;
import com.pluralsight.deli.enums.DrinkType;
import com.pluralsight.deli.interfaces.OrderItem;

public class Drink implements OrderItem {
    private DrinkSize size;
    private DrinkType type;
    private DrinkFlavor flavor;

    public Drink(DrinkSize size, DrinkType type, DrinkFlavor flavor) {
        this.size = size;
        this.type = type;
        this.flavor = flavor;
    }

    public DrinkSize getSize() {
        return size;
    }

    public DrinkType getType() {
        return type;
    }

    public DrinkFlavor getFlavor() {
        return flavor;
    }

    @Override
    public double getPrice() {
        //Using switch statement to return price of a Drink at different sizes
        return switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
        };
    }

    @Override
    public String printToReceipt() {
        return String.format("Drink: %s, %s, %s $%.2f\n", size, type, flavor, getPrice());
    }

    @Override
    public String toString() {
        return String.format("Drink Size: %s, Type: %s, Flavor: %s", size, type, flavor);
    }
}
