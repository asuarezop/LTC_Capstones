package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum SandwichSize {
    FOUR_INCHES("4"),
    EIGHT_INCHES("8"),
    TWELVE_INCHES("12");

    private static final Map<String, SandwichSize> BY_SIZE = new HashMap<>();

    //Pairing sandwichSize CONSTANTS with their given key values ()
    static {
        for (SandwichSize s : values()) {
            BY_SIZE.put(s.size, s);
        }
    }

    private final String size;

    SandwichSize(String value) {
        this.size = value;
    }

    //To get the value associated with a specific sandwichSize CONSTANT
    public String getSize() {
        return size;
    }

    //To get the sandwichSize CONSTANT value that matches user's input using a HashMap
    public static SandwichSize valueOfSize(String size) {
        //Retrieving the mapped value associated with given key String size
        return BY_SIZE.get(size);
    }
}
