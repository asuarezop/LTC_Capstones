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
        PrintScreenService.showLedgerHomeScreen(exitApp);
    }

    //Ledger Home Screen
    private static void showLedgerHomeScreen() {
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
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "D", "d":
                    try {
                        addDeposit(transactionsFilePath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "P", "p":
                    try {
                        makePayment(transactionsFilePath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
        } while (!exitApp);
    }

    //Ledger Screen
    private static void showLedgerScreen() {
        exitApp = false;
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
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "A", "a":
                    showAllEntries();
                    break;
                case "D", "d":
                    showOnlyDeposits();
                    break;
                case "P", "p":
                    showOnlyPayments();
                    break;
                case "L", "l":
                    showReportsScreen();
                    break;
                case "H", "h":
                    PrintScreenService.showLedgerHomeScreen(exitApp);
                    break;
                case "X", "x":
                    exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!exitApp);
    }

    private static void showReportsScreen() {
        exitApp = false;
        String reportsScreen = """
                ===================================================================================
                |                          * * * CACHE FLOW (REPORTS) * * *                       |
                |                                                                                 |
                |                              [1] Month to Month                                 |
                |                              [2] Previous Month                                 |
                |                              [3] Year to Date                                   |
                |                              [4] Previous Year                                  |
                |                              [5] Search By Vendor                               |
                |                                                                                 |
                |                          [H] Home          [X] Exit App                         |
                ===================================================================================
                """;

        do {
            System.out.println(reportsScreen + "Select from the available options: ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "H", "h":
                    PrintScreenService.showLedgerHomeScreen(exitApp);
                    break;
                case "X", "x":
                    exitApp = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!exitApp);
    }

    //Adding new deposits to the ledger
    private static void addDeposit(String filename) throws IOException {
        String[] depositDateTimeFormat;
        double transactionAmt;
        String vendorName;
        String transactionDesc;

        //Represents a new deposit transaction
        Transaction d;

        //Prompt user for deposit amount (could be its own method - prompt user)
        System.out.println("Enter the deposit amount from the transaction: ");
        userInput = inputSc.nextLine().trim();
        transactionAmt = Double.parseDouble(userInput);

        System.out.println("Enter the vendor name from the transaction: ");
        vendorName = inputSc.nextLine().trim();

        System.out.println("Enter the transaction description: ");
        transactionDesc = inputSc.nextLine().trim();

        //Call method to retrieve date and time
        depositDateTimeFormat = DateTimeHandlerService.getTransactionDateTime(transactionDateTime).split("\\|");

        bufWriter = FileHandlerService.getBufferedWriter(filename);

        if (!userInput.isEmpty()) {
            d = new Transaction(LocalDate.parse(depositDateTimeFormat[0]), LocalTime.parse(depositDateTimeFormat[1]), transactionDesc, vendorName, transactionAmt);

            bufWriter.write(d.getDateOfTransaction() + "|" + d.getTimeOfTransaction() + "|" + d.getTransactionDesc() + "|" + d.getVendor() + "|");
            bufWriter.write(String.format("%.2f \n", d.getAmount()));

            ledger.add(d);
            newEntries.add(d);
        }
        //Close the bufWriter
        bufWriter.close();
    }

    private static void makePayment(String filename) throws IOException {
        String[] paymentDateTimeFormat;
        double transactionAmt;
        String vendorName;
        String transactionDesc;

        //Represents a single debit payment
        Transaction p;

        System.out.println("Enter the debit amount from the transaction: ");
        //To showcase debits as a negative transaction
        transactionAmt = inputSc.nextDouble() * -1;
        inputSc.nextLine();

        System.out.println("Enter the vendor name from the transaction: ");
        vendorName = inputSc.nextLine().trim();

        System.out.println("Enter the transaction description: ");
        transactionDesc = inputSc.nextLine().trim();

        //Get date and time format for transaction input
        paymentDateTimeFormat = DateTimeHandlerService.getTransactionDateTime(transactionDateTime).split("\\|");

        bufWriter = FileHandlerService.getBufferedWriter(filename);

        if (!userInput.isEmpty()) {

            p = new Transaction(LocalDate.parse(paymentDateTimeFormat[0]), LocalTime.parse(paymentDateTimeFormat[1]), transactionDesc, vendorName, transactionAmt);

            bufWriter.write(p.getDateOfTransaction() + "|" + p.getTimeOfTransaction() + "|" + p.getTransactionDesc() + "|" + p.getVendor() + "|");
            bufWriter.write(String.format("%.2f \n", p.getAmount()));

            ledger.add(p);
            newEntries.add(p);
        }
        //Close the bufWriter
        bufWriter.close();
    }

    private static void showAllEntries() {
        //Print all transaction entries to the console
        for (Transaction t : ledger) {
            System.out.println("Date:" + t.getDateOfTransaction() + " Time:" + t.getTimeOfTransaction() + " Description:" + t.getTransactionDesc() + " Vendor:" + t.getVendor() + " Amount:" + t.getAmount());
        }
    }

    private static void showOnlyDeposits() {
        //Print only deposits (positive entries)
        for (Transaction d : ledger) {
            //If amount is not negative
            if (d.getAmount() > 0) {
                System.out.println("Date:" + d.getDateOfTransaction() + " Time:" + d.getTimeOfTransaction() + " Description:" + d.getTransactionDesc() + " Vendor:" + d.getVendor() + " Amount:" + d.getAmount());
            }
        }
    }

    private static void showOnlyPayments() {
        //Print only payments (negative entries)
        for (Transaction d : ledger) {
            //If amount is not positive (in the negative range)
            if (d.getAmount() < 0) {
                System.out.println("Date:" + d.getDateOfTransaction() + " Time:" + d.getTimeOfTransaction() + " Description:" + d.getTransactionDesc() + " Vendor:" + d.getVendor() + " Amount:" + d.getAmount());
            }
        }
    }
}
