package com.pluralsight.deli.enums;

import com.pluralsight.deli.interfaces.OrderItem;

import java.util.HashMap;
import java.util.Map;

public enum SauceType implements OrderItem {
    MAYO("A", "MYO"),
    MUSTARD ("B", "MUS"),
    KETCHUP ("C", "KTP"),
    RANCH ("D", "RNC"),
    THOUSAND_ISLANDS ("E", "THI"),
    VINAIGRETTE ("F", "VGT"),
    AU_JUS ("G", "AJS"),
    ITALIAN_DRESSING("H", "ITD"),
    MARINA("I", "MRN"),
    HONEY_MUSTARD("J", "HMD");

    private static final Map<String, SauceType> BY_CHOICE = new HashMap<>();

    static {
        for (SauceType t : values()) {
            BY_CHOICE.put(t.choice, t);
        }
    }

    private final String choice;
    private final String abbrev;

    SauceType(String userChoice, String abbrev) {
        this.choice = userChoice;
        this.abbrev = abbrev;
    }

    public static SauceType valueFromChoice(String value) {
        return BY_CHOICE.get(value);
    }

    public String getAbbrev() {
        return abbrev;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String printToReceipt() {
        return "";
    }
}
