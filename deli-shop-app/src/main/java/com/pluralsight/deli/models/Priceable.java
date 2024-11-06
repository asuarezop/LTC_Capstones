package com.pluralsight.deli.models;

 interface Priceable {
    default double getPrice() {
        return 0.0;
    }
}
