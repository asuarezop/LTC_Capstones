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
    AU_JUS ("G");

    private static final Map<String, SauceType> BY_CHOICE = new HashMap<>();

    //Pairing sauceType CONSTANTS with their given key values ()
    static {
        for (SauceType t : values()) {
            BY_CHOICE.put(t.choice, t);
        }
    }

    private final String choice;

    SauceType(String userChoice) {
        this.choice = userChoice;
    }

    //To get the sauceType CONSTANT value that matches user's input using a HashMap
    public static SauceType valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String choice
        return BY_CHOICE.get(value);
    }

    @Override
    public String printToReceipt() {
        return "";
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
