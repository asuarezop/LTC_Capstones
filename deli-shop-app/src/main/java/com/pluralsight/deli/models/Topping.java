package com.pluralsight.deli.models;

public interface Topping extends Priceable {
    String getToppingType();
    String getToppingCategory();
}