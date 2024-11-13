package com.pluralsight.deli.options;

import com.pluralsight.deli.models.OrderItem;

public enum SauceType implements OrderItem {
    MAYO,
    MUSTARD,
    KETCHUP,
    RANCH,
    THOUSAND_ISLANDS,
    VINAIGRETTE,
    AU_JUS;

    @Override
    public String printToReceipt() {
        return "";
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
