package com.pluralsight.deli.options;

public enum PaymentOption {
    CASH(100),
    DEBIT(100),
    CREDIT(100);

    private final int dollars;

    PaymentOption(int money) {
        this.dollars = money;
    }

    public int getDollars() {
        return dollars;
    }
}
