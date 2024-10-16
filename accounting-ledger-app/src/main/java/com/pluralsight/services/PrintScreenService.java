package com.pluralsight.services;

import com.pluralsight.ledger.LedgerApp;

public class PrintScreenService {

    //Ledger Home Screen
    public static void showLedgerHomeScreen(boolean exitApp) {
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
                case "D", "d":
                    System.out.println("Your inside deposit");
                    break;
                case "P", "p":
                    System.out.println("Your inside payment");
                    break;
                case "L", "l":
                    System.out.println("Your inside ledger");
                    break;
                case "X", "x":
                    exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!exitApp);
    }
}
