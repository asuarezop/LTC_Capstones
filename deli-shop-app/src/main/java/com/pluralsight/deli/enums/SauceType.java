package com.pluralsight.deli.enums;

import com.pluralsight.deli.interfaces.OrderItem;

import java.util.HashMap;
import java.util.Map;

public enum SauceType implements OrderItem {
    MAYO("A"),
    MUSTARD ("B"),
    KETCHUP ("C"),
    RANCH ("D"),
    THOUSAND_ISLANDS ("E"),
    VINAIGRETTE ("F"),
    AU_JUS ("G"),
    ITALIAN_DRESSING("H"),
    MARINA("I"),
    HONEY_MUSTARD("J");

    private static final Map<String, SauceType> BY_CHOICE = new HashMap<>();

    static {
        for (SauceType t : values()) {
            BY_CHOICE.put(t.choice, t);
        }
    }

    private final String choice;

    SauceType(String userChoice) {
        this.choice = userChoice;
    }

    public static SauceType valueFromChoice(String value) {
        return BY_CHOICE.get(value);
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String printToReceipt() {
        return "";
    }
}
