package com.pluralsight.deli.models;

import com.pluralsight.deli.services.DateTimeHandler;

import java.time.LocalDateTime;

public class POSManager {
    private static final LocalDateTime orderDateTime = LocalDateTime.now();
    private static String receiptsFilePath;

    public void saveOrder(Order o) {
        String fileTimestamp = DateTimeHandler.getOrderDateTime(orderDateTime);
        receiptsFilePath = "src/main/resources/receipts/" + fileTimestamp + ".txt";
    }

    //GET Endpoint to retrieve order details (/ORDER_DETAILS)
    public void getOrderDetails() {

    }

    //Printing to the user terminal or web front end interface
    public void printOrderReceipt() {

    }
}
