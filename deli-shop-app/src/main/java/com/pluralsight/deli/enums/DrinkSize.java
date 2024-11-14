package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum DrinkSize {
    SMALL ("A", "SML"),
    MEDIUM ("B", "MED"),
    LARGE ("C", "LRG");

    private static final Map<String, DrinkSize> BY_CHOICE = new HashMap<>();

    //Pairing drinkSize CONSTANTS with their given key values ()
    static {
        for (DrinkSize s : values()) {
            BY_CHOICE.put(s.choice, s);
        }
    }

    private final String choice;
    private final String abbrev;

    DrinkSize(String userChoice, String abbrev) {
        this.choice = userChoice;
        this.abbrev = abbrev;
    }

    public String getAbbrev() {
        return abbrev;
    }

    //To get the drinkSize CONSTANT value that matches user's input using a HashMap
    public static DrinkSize valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String choice
        return BY_CHOICE.get(value);
    }

}
