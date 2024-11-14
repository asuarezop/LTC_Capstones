package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum ChipFlavor {
    POTATO ("A"),
    BBQ ("B"),
    OVEN_BAKED ("C"),
    SUN_DRIED_TOMATO ("D"),
    SALT_N_PEPPER ("E"),
    MULTIGRAIN ("F"),
    CHEDDAR("G"),
    JALAPENO("H");

    private static final Map<String, ChipFlavor> BY_CHOICE = new HashMap<>();

    //Pairing chipFlavor CONSTANTS with their given key values ()
    static {
        for (ChipFlavor f : values()) {
            BY_CHOICE.put(f.choice, f);
        }
    }

    private final String choice;

    ChipFlavor(String userChoice) {
        this.choice = userChoice;
    }

    //To get the chipFlavor CONSTANT value that matches user's input using a HashMap
    public static ChipFlavor valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String choice
        return BY_CHOICE.get(value);
    }
}
