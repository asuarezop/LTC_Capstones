package com.pluralsight.deli.options;

import com.pluralsight.deli.models.Topping;

public enum PremiumTopping implements Topping {
    STEAK,
    HAM,
    SALAMI,
    ROAST_BEEF,
    CHICKEN,
    BACON,
    AMERICAN_CHEESE,
    PROVOLONE_CHEESE,
    CHEDDAR_CHEESE,
    SWISS_CHEESE;

    @Override
    public void getCategory() {

    }
}
