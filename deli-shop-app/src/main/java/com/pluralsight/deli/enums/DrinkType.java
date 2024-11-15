package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum DrinkType {
    WATER ("A"),
    SOFT_DRINK ("B"),
    ICED_TEA ("C");

    private static final Map<String, DrinkType> BY_CHOICE = new HashMap<>();

    static {
        for (DrinkType t : values()) {
            BY_CHOICE.put(t.choice, t);
        }
    }

    private final String choice;

    DrinkType(String userChoice) {
        this.choice = userChoice;
    }

    public static DrinkType valueFromChoice(String value) {
        return BY_CHOICE.get(value);
    }
}
