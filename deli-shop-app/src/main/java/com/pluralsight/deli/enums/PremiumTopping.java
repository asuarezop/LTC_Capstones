package com.pluralsight.deli.enums;

import com.pluralsight.deli.interfaces.Topping;

import java.util.HashMap;
import java.util.Map;

public enum PremiumTopping implements Topping {
    STEAK ("A","Meat"),
    HAM ("B","Meat"),
    SALAMI ("C","Meat"),
    PEPPERONI("D", "Meat"),
    TURKEY("E", "Meat"),
    MEATBALL("F", "Meat"),
    ROAST_BEEF ("G","Meat"),
    CHICKEN ("H","Meat"),
    BACON ("I","Meat"),
    AMERICAN ("J","Cheese"),
    PROVOLONE ("K","Cheese"),
    CHEDDAR ("L","Cheese"),
    SWISS ("M","Cheese"),
    MOZZARELLA("N", "Cheese"),
    PARMESAN("O", "Cheese");

    private static final Map<String, PremiumTopping> BY_CHOICE = new HashMap<>();

    //Mapping premiumTopping CONSTANTS to userChoice key values ()
    static {
        for (PremiumTopping pt : values()) {
            BY_CHOICE.put(pt.choice, pt);
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
