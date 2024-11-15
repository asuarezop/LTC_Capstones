package com.pluralsight.deli.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {
    //To retrieve current date and time for receipts file
    public static String getFileTimeStamp(LocalDateTime orderDateTime) {

        DateTimeFormatter traditionalDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        String orderDate = orderDateTime.format(traditionalDate);

        DateTimeFormatter traditionalTime = DateTimeFormatter.ofPattern("hhmmss");
        String orderTime = orderDateTime.format(traditionalTime);

        return orderDate + "-" + orderTime;
    }

    //To retrieve current date and time for order receipt in a readable format
    public static String getReceiptDateTime(LocalDateTime orderDateTime) {
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String receiptDate = orderDateTime.format(formattedDate);

        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm a");
        String receiptTime = orderDateTime.format(formattedTime);

        return receiptDate + "|" + receiptTime;
    }
}
