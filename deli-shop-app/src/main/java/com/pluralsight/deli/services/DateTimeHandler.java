package com.pluralsight.deli.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {
    //To retrieve current date and time for an order
    public static String getOrderDateTime(LocalDateTime orderDateTime) {

        DateTimeFormatter traditionalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String orderDate = orderDateTime.format(traditionalDate);

        DateTimeFormatter traditionalTime = DateTimeFormatter.ofPattern("HH:mm");
        String orderTime = orderDateTime.format(traditionalTime);

        return orderDate + "|" + orderTime;
    }
}
