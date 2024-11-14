package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum DrinkType {
    WATER ("A"),
    SOFT_DRINK ("B"),
    ICED_TEA ("C");

    private static final Map<String, DrinkType> BY_CHOICE = new HashMap<>();

    //Pairing drinkType CONSTANTS with their given key values ()
    static {
        for (DrinkType t : values()) {
            BY_CHOICE.put(t.choice, t);
        }
    }

    private final String choice;

    DrinkType(String userChoice) {
        this.choice = userChoice;
    }

    //To get the drinkType CONSTANT value that matches user's input using a HashMap
    public static DrinkType valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String choice
        return BY_CHOICE.get(value);
    }
}
