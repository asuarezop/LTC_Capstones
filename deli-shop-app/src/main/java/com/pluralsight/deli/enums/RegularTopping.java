package com.pluralsight.deli.enums;

import com.pluralsight.deli.interfaces.Topping;

public enum RegularTopping implements Topping {
    LETTUCE ("Veggies"),
    PEPPERS ("Veggies"),
    ONIONS ("Veggies"),
    TOMATOES ("Veggies"),
    JALAPENOS ("Veggies"),
    CUCUMBERS ("Veggies"),
    PICKLES ("Veggies"),
    GUACAMOLE ("Veggies"),
    MUSHROOMS ("Veggies");

    private final String toppingCategory;

    RegularTopping(String category) {
        this.toppingCategory = category;
    }

    @Override
    public double getPrice() {
        return 0.0;
    }

    @Override
    public String getToppingType() {
        return "Regular";
    }

    @Override
    public String getToppingCategory() {
        return toppingCategory;
    }
}
