package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum SandwichSize {
    FOUR_INCHES("A"),
    EIGHT_INCHES("B"),
    TWELVE_INCHES("C");

    private static final Map<String, SandwichSize> BY_CHOICE = new HashMap<>();

    //Pairing sandwichSize CONSTANTS with their given key values ()
    static {
        for (SandwichSize s : values()) {
            BY_CHOICE.put(s.choice, s);
        }
    }

    private final String choice;

    SandwichSize(String userChoice) {
        this.choice = userChoice;
    }

    //To get the value associated with a specific sandwichSize CONSTANT
    public String getChoice() {
        return choice;
    }

    //To get the sandwichSize CONSTANT value that matches user's input using a HashMap
    public static SandwichSize valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String size
        return BY_CHOICE.get(value);
    }
}
