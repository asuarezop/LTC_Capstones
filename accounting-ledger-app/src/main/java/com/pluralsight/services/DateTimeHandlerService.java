package com.pluralsight.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandlerService {

    //To retrieve current date and time for a transaction
    public static String getTransactionDateTime(LocalDateTime transactionDateTime) {

        DateTimeFormatter traditionalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String transactionDate = transactionDateTime.format(traditionalDate);

        DateTimeFormatter traditionalTime = DateTimeFormatter.ofPattern("HH:mm");
        String transactionTime = transactionDateTime.format(traditionalTime);

        return transactionDate + "|" + transactionTime;
    }
}
