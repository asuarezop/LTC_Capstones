package com.pluralsight.deli.enums;

import com.pluralsight.deli.interfaces.Topping;

public enum PremiumTopping implements Topping {
    STEAK ("Meat"),
    HAM ("Meat"),
    SALAMI ("Meat"),
    ROAST_BEEF ("Meat"),
    CHICKEN ("Meat"),
    BACON ("Meat"),
    AMERICAN ("Cheese"),
    PROVOLONE ("Cheese"),
    CHEDDAR ("Cheese"),
    SWISS ("Cheese");

    private final String toppingCategory;

    PremiumTopping(String category) {
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
