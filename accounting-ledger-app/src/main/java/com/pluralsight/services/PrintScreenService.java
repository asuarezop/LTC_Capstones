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
                    System.out.println("Your inside ledger");
                    break;
                case "X", "x":
                    LedgerApp.exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!LedgerApp.exitApp);
    }

    public static void showLedgerScreen() {
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
    }

    //Retrieves user input from a prompt
    public static String promptUser(String prompt) {
        System.out.println(prompt);
        return LedgerApp.userInput = LedgerApp.inputSc.nextLine().trim();
    }
}
