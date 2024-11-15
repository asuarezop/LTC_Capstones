package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum DrinkSize {
    SMALL ("A", "SML"),
    MEDIUM ("B", "MED"),
    LARGE ("C", "LRG");

    private static final Map<String, DrinkSize> BY_CHOICE = new HashMap<>();

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

    public static DrinkSize valueFromChoice(String value) {
        return BY_CHOICE.get(value);
    }

}
