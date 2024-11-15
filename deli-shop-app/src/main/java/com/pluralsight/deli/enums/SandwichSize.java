package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum SandwichSize {
    FOUR_INCHES("A", 4),
    EIGHT_INCHES("B", 8),
    TWELVE_INCHES("C", 12);

    private static final Map<String, SandwichSize> BY_CHOICE = new HashMap<>();

    //Pairing sandwichSize CONSTANTS with their given key values ()
    static {
        for (SandwichSize s : values()) {
            BY_CHOICE.put(s.choice, s);
        }
    }

    private final String choice;
    private final int size;

    SandwichSize(String userChoice, int size) {
        this.choice = userChoice;
        this.size = size;
    }

    //To get the value associated with a specific sandwichSize CONSTANT
    public String getChoice() {
        return choice;
    }

    public int getSize() { return size; }

    //To get the sandwichSize CONSTANT value that matches user's input using a HashMap
    public static SandwichSize valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String size
        return BY_CHOICE.get(value);
    }
}
