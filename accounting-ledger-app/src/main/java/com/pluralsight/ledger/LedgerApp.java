package com.pluralsight.ledger;

import com.pluralsight.models.Transaction;
import com.pluralsight.services.DateTimeHandlerService;
import com.pluralsight.services.FileHandlerService;
import com.pluralsight.services.PrintScreenService;

import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerApp {

    public static Scanner inputSc;
    public static String userInput;
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
            bufWriter = FileHandlerService.getBufferedWriter(transactionsFilePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Call showLedgerHomeScreen to display application home screen
        PrintScreenService.showLedgerHomeScreen();
    }

//    //Ledger Screen
//    private static void showLedgerScreen() throws IOException {
//        exitApp = false;
//        String ledgerScreen = """
//                ===================================================================================
//                |                          * * * CACHE FLOW (LEDGER) * * *                        |
//                |                                                                                 |
//                |                              [A] Display All Entries                            |
//                |                              [D] Show Only Deposits                             |
//                |                              [P] Show Payments                                  |
//                |                              [L] Run Reports                                    |
//                |                                                                                 |
//                |                          [H] Home          [X] Exit App                         |
//                ===================================================================================
//                """;
//
//        do {
//            System.out.print(ledgerScreen + "Select from the available options: ");
//            userInput = inputSc.nextLine().trim();
//
//            switch (userInput) {
//                case "A", "a":
//                    showAllEntries();
//                    break;
//                case "D", "d":
//                    showOnlyDeposits();
//                    break;
//                case "P", "p":
//                    showOnlyPayments();
//                    break;
//                case "L", "l":
//                    showReportsScreen();
//                    break;
//                case "H", "h":
//                    PrintScreenService.showLedgerHomeScreen();
//                    break;
//                case "X", "x":
//                    exitApp = true;
//                    break;
//                default:
//                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
//            }
//        } while (!exitApp);
//    }

//    private static void showReportsScreen() throws IOException {
//        exitApp = false;
//        String reportsScreen = """
//                ===================================================================================
//                |                          * * * CACHE FLOW (REPORTS) * * *                       |
//                |                                                                                 |
//                |                              [1] Month to Month                                 |
//                |                              [2] Previous Month                                 |
//                |                              [3] Year to Date                                   |
//                |                              [4] Previous Year                                  |
//                |                              [5] Search By Vendor                               |
//                |                                                                                 |
//                |                          [H] Home          [X] Exit App                         |
//                ===================================================================================
//                """;
//
//        do {
//            System.out.println(reportsScreen + "Select from the available options: ");
//            userInput = inputSc.nextLine().trim();
//
//            switch (userInput) {
//                case "1":
//                    break;
//                case "2":
//                    break;
//                case "3":
//                    break;
//                case "4":
//                    break;
//                case "5":
//                    break;
//                case "H", "h":
//                    PrintScreenService.showLedgerHomeScreen();
//                    break;
//                case "X", "x":
//                    exitApp = true;
//                    break;
//                default:
//                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
//            }
//        } while (!exitApp);
//    }
}
