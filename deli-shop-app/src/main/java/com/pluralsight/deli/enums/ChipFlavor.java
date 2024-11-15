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

    static {
        for (ChipFlavor f : values()) {
            BY_CHOICE.put(f.choice, f);
        }
    }

    private final String choice;

    ChipFlavor(String userChoice) {
        this.choice = userChoice;
    }

    public static ChipFlavor valueFromChoice(String value) {
        return BY_CHOICE.get(value);
    }
}
