package com.pluralsight.ledger;

import com.pluralsight.models.Transaction;
import com.pluralsight.services.FileHandlerService;
import com.pluralsight.services.PrintScreenService;

import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerApp {

    public static Scanner inputSc;
    public static String userInput;
    public static String escapeKey = "\033";
    public static boolean exitApp;

    public static ArrayList<Transaction> ledger;
    public static ArrayList<Transaction> newEntries;

    public static BufferedWriter bufWriter;
    public static LocalDateTime transactionDateTime;
    public static String transactionsFilePath;

    public static void main(String[] args) throws IOException {
        inputSc = new Scanner(System.in);

        //Initializing transactionsList
        ledger = new ArrayList<>();

        newEntries = new ArrayList<>();

        //File path for transactions data
        transactionsFilePath = "src/main/resources/transactions.csv";

        //Read transaction file and store contents into transactionsList
        ledger = FileHandlerService.readTransactionFile(transactionsFilePath);

        //Initializing date time to now
        transactionDateTime = LocalDateTime.now();

        //Boolean condition to control printScreens
        exitApp = false;

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
