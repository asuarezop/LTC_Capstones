package com.pluralsight.services;

import com.pluralsight.ledger.LedgerApp;

import java.io.IOException;

public class PrintScreenService {

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
            System.out.print(homeScreen + "Select from the available options: ");
            LedgerApp.userInput = LedgerApp.inputSc.nextLine().trim();

            switch (LedgerApp.userInput) {
                case "D", "d", "P", "p":
                    TransactionProcessingService.addTransaction(LedgerApp.userInput);
                    break;
                case "L", "l":
                    showLedgerScreen();
                    break;
                case "X", "x":
                    LedgerApp.exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!LedgerApp.exitApp);
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
            System.out.print(ledgerScreen + "Select from the available options: ");
            LedgerApp.userInput = LedgerApp.inputSc.nextLine().trim();

            switch (LedgerApp.userInput) {
                case "A", "a", "D", "d", "P", "p":
                    TransactionProcessingService.showTransactionsFromLedger(LedgerApp.userInput);
                    break;
                case "L", "l":
                    showReportsScreen();
                case "H", "h":
                    showLedgerHomeScreen();
                    break;
                case "X", "x":
                    LedgerApp.exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!LedgerApp.exitApp);
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
            System.out.print(reportsScreen + "Select from the available options: ");
            LedgerApp.userInput = LedgerApp.inputSc.nextLine().trim();

            switch (LedgerApp.userInput) {
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
                    LedgerApp.exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!LedgerApp.exitApp);
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
            System.out.println(customSearchScreen + "Select from the available options: ");
            LedgerApp.userInput = LedgerApp.inputSc.nextLine().trim();

            switch (LedgerApp.userInput) {
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
                    LedgerApp.exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!LedgerApp.exitApp);
    }

    //Retrieves user input from a prompt
    public static String promptUser(String prompt) {
        System.out.println(prompt);
        return LedgerApp.userInput = LedgerApp.inputSc.nextLine().trim();
    }
}
