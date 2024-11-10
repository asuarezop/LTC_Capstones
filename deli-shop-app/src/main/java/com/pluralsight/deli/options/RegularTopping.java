package com.pluralsight.deli.options;

import com.pluralsight.deli.models.Topping;

public enum RegularTopping implements Topping {
    LETTUCE,
    PEPPERS,
    ONIONS,
    TOMATOES,
    JALAPENOS,
    CUCUMBERS,
    PICKLES,
    GUACAMOLE,
    MUSHROOMS;

    @Override
    public double getPrice() {
        return 0.0;
    }
}
