package com.pluralsight.deli.enums;

import com.pluralsight.deli.interfaces.OrderItem;

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
