package com.pluralsight.deli.models;

public interface Priceable {
    default double getPrice() {
        return 0.0;
    }
}
