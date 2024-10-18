package com.pluralsight.ledger;

import com.pluralsight.models.Transaction;
import com.pluralsight.services.FileHandlerService;
import com.pluralsight.services.PrintScreenService;
import java.io.*;
import java.time.*;
import java.util.ArrayList;

public class LedgerApp {
    public static String escapeKey = "\033";
    public static ArrayList<Transaction> ledger;
    public static BufferedWriter bufWriter;
    public static LocalDateTime transactionDateTime;
    public static String transactionsFilePath;

    public static void main(String[] args) throws IOException {
        //Initializing ledger
        ledger = new ArrayList<>();

        //File path for transactions data
        transactionsFilePath = "src/main/resources/transactions.csv";

        //Read transaction file and store contents into ledger
        ledger = FileHandlerService.readTransactionFile(transactionsFilePath);

        //Initializing transactionDateTime
        transactionDateTime = LocalDateTime.now();

        try {
            //Initializing bufferedWriter
            bufWriter = FileHandlerService.getBufferedWriter(transactionsFilePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Call showLedgerHomeScreen to display application home screen
        PrintScreenService.showLedgerHomeScreen();
    }
}
