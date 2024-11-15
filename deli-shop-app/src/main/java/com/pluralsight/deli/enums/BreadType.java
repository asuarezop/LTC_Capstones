package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum BreadType {
    WHITE ("A", "WHI"),
    WHEAT ("B", "WHT"),
    RYE ("C", "RYE"),
    WRAP ("D", "WRP"),
    CIABATTA("E", "CIB"),
    BAGUETTE("F", "BGT"),
    MULTIGRAIN("G", "MGN");

    private static final Map<String, BreadType> BY_CHOICE = new HashMap<>();

    //Pairing breadType CONSTANTS with their given key values ()
    static {
        for (BreadType b : values()) {
            BY_CHOICE.put(b.choice, b);
        }
    }

    private final String choice;
    private final String abbrev;

    BreadType(String userChoice, String abbrev) {
        this.choice = userChoice;
        this.abbrev = abbrev;
    }

    public String getAbbrev() {
        return abbrev;
    }

    //To get the breadType CONSTANT value that matches user's input using a HashMap
    public static BreadType valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String choice
        return BY_CHOICE.get(value);
    }
}
