package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum DrinkSize {
    SMALL ("A"),
    MEDIUM ("B"),
    LARGE ("C");

    private static final Map<String, DrinkSize> BY_CHOICE = new HashMap<>();

    //Pairing drinkSize CONSTANTS with their given key values ()
    static {
        for (DrinkSize s : values()) {
            BY_CHOICE.put(s.choice, s);
        }
    }

    private final String choice;

    DrinkSize(String userChoice) {
        this.choice = userChoice;
    }

    //To get the drinkSize CONSTANT value that matches user's input using a HashMap
    public static DrinkSize valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String choice
        return BY_CHOICE.get(value);
    }

}
