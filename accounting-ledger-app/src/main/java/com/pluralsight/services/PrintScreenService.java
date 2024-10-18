package com.pluralsight.services;

import com.pluralsight.ledger.LedgerApp;
import com.pluralsight.models.Transaction;
import java.io.IOException;
import java.util.Scanner;

public class PrintScreenService {
    //String variables to color terminal screens and prompt output
    public static String allScreensColor = LedgerApp.escapeKey + "[38;5;153m";
    public static String promptTextColor = LedgerApp.escapeKey + "[38;5;231m";

    //Boolean condition to exit application screens
    public static boolean exitApp = false;

    //Related to input from user
    public static String userInput;

    //Initializing scanner to read from terminal input
    public static Scanner inputSc = new Scanner(System.in);

    //Ledger Home Screen
    public static void showLedgerHomeScreen() throws IOException {
        String homeScreen = """
                ===================================================================================
                |                          * * * CACHE FLOW (HOME) * * *                          |
                |                                                                                 |
                |                              [D] Add Deposit                                    |
                |                              [P] Make Payment (Debit)                           |
                |                              [L] Show Ledger                                    |
                |                              [X] Exit App                                       |
                |                                                                                 |
                ===================================================================================
                """;

        do {
            System.out.print(allScreensColor + homeScreen + "Select from the available options: ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "D", "d", "P", "p":
                    TransactionProcessingService.addTransaction(userInput);
                    break;
                case "L", "l":
                    showLedgerScreen();
                    break;
                case "X", "x":
                    exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!exitApp);
    }

    public static void showLedgerScreen() throws IOException {
        String ledgerScreen = """
                ===================================================================================
                |                          * * * CACHE FLOW (LEDGER) * * *                        |
                |                                                                                 |
                |                              [A] Display All Entries                            |
                |                              [D] Show Only Deposits                             |
                |                              [P] Show Payments                                  |
                |                              [L] Run Reports                                    |
                |                                                                                 |
                |                          [H] Home          [X] Exit App                         |
                ===================================================================================
                """;

        do {
            System.out.print(allScreensColor + ledgerScreen + "Select from the available options: ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "A", "a", "D", "d", "P", "p":
                    TransactionProcessingService.showTransactionsFromLedger(userInput);
                    break;
                case "L", "l":
                    showReportsScreen();
                case "H", "h":
                    showLedgerHomeScreen();
                    break;
                case "X", "x":
                    exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!exitApp);
    }

    public static void showReportsScreen() throws IOException {
        String reportsScreen = """
                ===================================================================================
                |                          * * * CACHE FLOW (REPORTS) * * *                       |
                |                                                                                 |
                |                              [1] Month to Date                                  |
                |                              [2] Previous Month                                 |
                |                              [3] Year to Date                                   |
                |                              [4] Previous Year                                  |
                |                              [5] Search By Vendor                               |
                |                                                                                 |
                |                          [H] Home          [X] Exit App                         |
                ===================================================================================
                """;
        do {
            System.out.print(allScreensColor + reportsScreen + "Select from the available options: ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    TransactionProcessingService.monthToDateTransactionSearch();
                    break;
                case "2":
                    TransactionProcessingService.previousMonthTransactionSearch();
                    break;
                case "3":
                    TransactionProcessingService.yearToDateTransactionSearch();
                    break;
                case "4":
                    TransactionProcessingService.previousYearTransactionSearch();
                    break;
                case "5":
                    TransactionProcessingService.searchTransactionByVendor();
                    break;
                case "6":
                    showCustomSearchScreen();
                    break;
                case "H", "h":
                    showLedgerHomeScreen();
                    break;
                case "X", "x":
                    exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!exitApp);
    }

    public static void showCustomSearchScreen() throws IOException {
        String customSearchScreen = """
                ===================================================================================
                |                     * * * CACHE FLOW (CUSTOM SEARCH) * * *                      |
                |                                                                                 |
                |                              [1] Start/End Date                                 |
                |                              [2] Description                                    |
                |                              [3] Vendor                                         |
                |                              [4] Amount                                         |
                |                                                                                 |
                |                          [H] Home          [X] Exit App                         |
                ===================================================================================
                """;

        do {
            System.out.println(allScreensColor + customSearchScreen + "Select from the available options: ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    TransactionProcessingService.searchTransactionByStartEndDate();
                    break;
                case "2":
                    System.out.println("Description search");
                    break;
                case "3":
                    System.out.println("Vendor search");
                    break;
                case "4":
                    System.out.println("Amount search");
                    break;
                case "H", "h":
                    showLedgerHomeScreen();
                    break;
                case "X", "x":
                    exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!exitApp);
    }

    //Retrieves user input from a prompt
    public static String promptUser(String prompt) {
        System.out.println(promptTextColor + prompt);
        return userInput = inputSc.nextLine().trim();
    }

    //Print out a formatted transaction
    public static void printTransaction(Transaction t) {
        String formatStr = t.toString();
        System.out.println(formatStr);
    }

    public static String promptUserForTransactionDetails() {
        double transactionDepositAmt;
        String transactionDesc;
        String vendorName;

        transactionDepositAmt = Double.parseDouble(promptUser("Enter the deposit amount: "));
        transactionDesc = promptUser("Enter the transaction description: ");
        vendorName = promptUser("Enter the vendor name: ");

        return transactionDepositAmt + "|" + transactionDesc + "|" + vendorName;
    }
}
