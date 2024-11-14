package com.pluralsight.deli.enums;

public enum PaymentOption {
    CASH(100),
    DEBIT(100),
    CREDIT(100);

    private final double dollars;

    PaymentOption(double money) {
        this.dollars = money;
    }

    public double getDollars() {
        return dollars;
    }
}
