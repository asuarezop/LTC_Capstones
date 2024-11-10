package com.pluralsight.deli.options;

import com.pluralsight.deli.models.Topping;

public enum PremiumTopping implements Topping {
    STEAK,
    HAM,
    SALAMI,
    ROAST_BEEF,
    CHICKEN,
    BACON,
    AMERICAN,
    PROVOLONE,
    CHEDDAR,
    SWISS;

    @Override
    public double getPrice() {
        return 0;
    }
}
