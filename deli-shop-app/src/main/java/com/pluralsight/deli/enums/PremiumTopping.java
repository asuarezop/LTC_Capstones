package com.pluralsight.deli.enums;

import com.pluralsight.deli.interfaces.Topping;

import java.util.HashMap;
import java.util.Map;

public enum PremiumTopping implements Topping {
    STEAK ("A","Meat"),
    HAM ("B","Meat"),
    SALAMI ("C","Meat"),
    ROAST_BEEF ("D","Meat"),
    CHICKEN ("E","Meat"),
    BACON ("F","Meat"),
    AMERICAN ("A","Cheese"),
    PROVOLONE ("B","Cheese"),
    CHEDDAR ("C","Cheese"),
    SWISS ("D","Cheese");

    private static final Map<String, PremiumTopping> BY_CHOICE = new HashMap<>();

    //Mapping premiumTopping CONSTANTS to userChoice key values ()
    static {
        for (PremiumTopping t : values()) {
            BY_CHOICE.put(t.choice, t);
        }
    }

    //To get the premiumTopping CONSTANT value that matches user's input using a HashMap
    public static PremiumTopping valueFromChoice(String value) {
        //Retrieving the mapped value associated with given key String choice
        return BY_CHOICE.get(value);
    }

    private final String choice;
    private final String toppingCategory;

    PremiumTopping(String userChoice, String category) {
        this.choice = userChoice;
        this.toppingCategory = category;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getToppingType() {
        return "Premium";
    }

    @Override
    public String getToppingCategory() {
        return toppingCategory;
    }
}
