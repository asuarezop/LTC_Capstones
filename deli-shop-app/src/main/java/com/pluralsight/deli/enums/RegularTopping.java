package com.pluralsight.deli.enums;

import com.pluralsight.deli.interfaces.Topping;

import java.util.HashMap;
import java.util.Map;

public enum RegularTopping implements Topping {
    LETTUCE ("A","Veggies"),
    PEPPERS ("B","Veggies"),
    ONIONS ("C","Veggies"),
    TOMATOES ("D","Veggies"),
    JALAPENOS ("E","Veggies"),
    CUCUMBERS ("F","Veggies"),
    PICKLES ("G","Veggies"),
    GUACAMOLE ("H","Veggies"),
    MUSHROOMS ("I","Veggies"),
    OLIVES("J", "Veggies");

    private static final Map<String, RegularTopping> BY_CHOICE = new HashMap<>();

    static {
        for (RegularTopping t : values()) {
            BY_CHOICE.put(t.choice, t);
        }
    }

    private final String choice;
    private final String toppingCategory;

    RegularTopping(String userChoice, String category) {
        this.choice = userChoice;
        this.toppingCategory = category;
    }

    public static RegularTopping valueFromChoice(String value) {
        return BY_CHOICE.get(value);
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
