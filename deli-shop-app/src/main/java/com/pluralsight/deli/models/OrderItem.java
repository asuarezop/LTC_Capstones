package com.pluralsight.deli.models;

public interface OrderItem extends Priceable {
    String printToReceipt();
}
