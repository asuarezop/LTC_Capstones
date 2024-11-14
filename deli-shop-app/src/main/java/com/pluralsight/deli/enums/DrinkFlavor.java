package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum DrinkFlavor {
    COLA ("A"),
    LEMON_LIME ("B"),
    ORANGE ("C"),
    DR_PEPPER ("D"),
    FRUIT_PUNCH ("E"),
    BERRY ("F"),
    ROOT_BEER ("G"),
    LEMONADE ("H"),
    LEMON_WATER ("I"),
    SELTZER_WATER ("J"),
    CUCUMBER_WATER ("K"),
    SWEET_TEA ("L"),
    LEMON_TEA ("M"),
    GREEN_TEA ("N");

    private static final Map<String, DrinkFlavor> BY_CHOICE = new HashMap<>();

    //Pairing drinkFlavor CONSTANTS with their given key values ()
    static {
        for (DrinkFlavor f : values()) {
            BY_CHOICE.put(f.choice, f);
        }
    }

    private final String choice;

    DrinkFlavor(String userChoice) {
        this.choice = userChoice;
    }

    //To get the drinkFlavor CONSTANT value that matches user's input using a HashMap
    public static DrinkFlavor valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String choice
        return BY_CHOICE.get(value);
    }
}
