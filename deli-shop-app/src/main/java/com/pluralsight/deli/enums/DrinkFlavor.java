package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum DrinkFlavor {
    COLA ("A", "CL"),
    LEMON_LIME ("B", "LL"),
    ORANGE ("C", "ORG"),
    DR_PEPPER ("D", "DP"),
    FRUIT_PUNCH ("E", "FP"),
    BERRY ("F", "BRY"),
    ROOT_BEER ("G", "RB"),
    LEMONADE ("H", "LEM"),
    LEMON_WATER ("I", "LW"),
    SELTZER_WATER ("J", "SW"),
    CUCUMBER_WATER ("K", "CW"),
    SWEET_TEA ("L", "ST"),
    LEMON_TEA ("M", "LT"),
    GREEN_TEA ("N", "GT");

    private static final Map<String, DrinkFlavor> BY_CHOICE = new HashMap<>();

    //Pairing drinkFlavor CONSTANTS with their given key values ()
    static {
        for (DrinkFlavor f : values()) {
            BY_CHOICE.put(f.choice, f);
        }
    }

    private final String choice;
    private final String abbrev;

    DrinkFlavor(String userChoice, String abbrev) {
        this.choice = userChoice;
        this.abbrev = abbrev;
    }

    public String getAbbrev() {
        return abbrev;
    }

    //To get the drinkFlavor CONSTANT value that matches user's input using a HashMap
    public static DrinkFlavor valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String choice
        return BY_CHOICE.get(value);
    }
}
