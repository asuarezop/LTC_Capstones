package com.pluralsight.deli.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentOption {
    CASH("1", 100),
    DEBIT("2",100),
    CREDIT("3", 100);

    private static final Map<String, PaymentOption> BY_CHOICE = new HashMap<>();

    static {
        for (PaymentOption p : values()) {
            BY_CHOICE.put(p.selection, p);
        }
    }

    private final String selection;
    private final double dollars;

    PaymentOption(String choice, double money) {
        this.selection = choice;
        this.dollars = money;
    }

    public double getDollars() {
        return dollars;
    }

    public static PaymentOption valueFromChoice(String value) {
        return BY_CHOICE.get(value);
    }
}
