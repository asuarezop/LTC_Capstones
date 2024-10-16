package com.pluralsight.ledger;

import com.pluralsight.models.Transaction;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerApp {

    public static Scanner inputSc;
    public static String userInput;
    public static boolean exitApp;

    public static ArrayList<Transaction> ledger;
    public static String transactionsFilePath;

    public static BufferedWriter bufWriter;
    public static LocalDateTime transactionDateTime;

    public static void main(String[] args) throws IOException {
        inputSc = new Scanner(System.in);

        //Initializing transactionsList
        ledger = new ArrayList<>();

        //File path for transactions data
        transactionsFilePath = "src/main/resources/transactions.csv";

        //Read transaction file and store contents into transactionsList
        ledger = readTransactionFile(transactionsFilePath);

        //Initializing date time to now
        transactionDateTime = LocalDateTime.now();

        try {
            bufWriter = getBufferedWriter(transactionsFilePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Call showLedgerHomeScreen to display application home screen
        showLedgerHomeScreen();
    }

    //Ledger Home Screen
    private static void showLedgerHomeScreen() {
        exitApp = false;
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
                        addDeposit(ledger, transactionsFilePath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "P", "p":
                    try {
                        makePayment(ledger, transactionsFilePath);
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
        } while (!exitApp);
    }

    //Adding new deposits to the ledger
    private static void addDeposit(ArrayList<Transaction> ledger, String filename) throws IOException {
        String[] depositDateTimeFormat;
        double transactionAmt;
        String vendorName;
        String transactionDesc;
        String pastTransactionsTimestamp;





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
        depositDateTimeFormat = getTransactionDateTime(transactionDateTime).split("\\|");

        pastTransactionsTimestamp = depositDateTimeFormat[0] + depositDateTimeFormat[1];

        String backupFilePath = "src/main/resources/backups/" + pastTransactionsTimestamp + ".csv";

        bufWriter = getBufferedWriter(filename);

        BufferedWriter bufWriter2 = getBufferedWriter(backupFilePath);

        if (!userInput.isEmpty()) {
            d = new Transaction(LocalDate.parse(depositDateTimeFormat[0]), LocalTime.parse(depositDateTimeFormat[1]), transactionDesc, vendorName, transactionAmt);

            //Write to bufferedWriter header of csv file
            bufWriter.write("Date|Time|Description|Vendor|Amount\n");
            bufWriter.write(d.getDateOfTransaction() + "|" + d.getTimeOfTransaction() + "|" + d.getTransactionDesc() + "|" + d.getVendor() + "|");
            bufWriter.write(String.format("%.2f \n", d.getAmount()));

            bufWriter2.write(d.getDateOfTransaction() + "|" + d.getTimeOfTransaction() + "|" + d.getTransactionDesc() + "|" + d.getVendor() + "|");
            bufWriter2.write(String.format("%.2f \n", d.getAmount()));
            
            ledger.add(d);
        }
        //Close the bufWriter
        bufWriter.close();
    }

    private static void makePayment(ArrayList<Transaction> ledger, String filename) throws IOException {
        String[] paymentDateTimeFormat;
        double transactionAmt;
        String vendorName;
        String transactionDesc;

        //Represents a single debit payment
        Transaction p;

        System.out.println("Enter the debit amount from the transaction: ");
        //To showcase debits as a negative transaction
        transactionAmt = inputSc.nextDouble();

        System.out.println("Enter the vendor name from the transaction: ");
        vendorName = inputSc.nextLine().trim();

        System.out.println("Enter the transaction description: ");
        transactionDesc = inputSc.nextLine().trim();

        //Get date and time format for transaction input
        paymentDateTimeFormat = getTransactionDateTime(transactionDateTime).split("\\|");

        bufWriter = getBufferedWriter(filename);

        if (!userInput.isEmpty()) {

            p = new Transaction(LocalDate.parse(paymentDateTimeFormat[0]), LocalTime.parse(paymentDateTimeFormat[1]), transactionDesc, vendorName, transactionAmt);

            bufWriter.write(p.getDateOfTransaction() + "|" + p.getTimeOfTransaction() + "|" + p.getTransactionDesc() + "|" + p.getVendor() + "|");
            bufWriter.write(String.format("%.2f \n", p.getAmount()));

            ledger.add(p);
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

    private static ArrayList<Transaction> readTransactionFile(String filename) {
        //Represents a single transaction to be added
        Transaction t;

        //Store a read-copy of transactions into ArrayList
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            //Calling openFileReader method to initialize BufferedReader
            BufferedReader bufReader = openFileReader(filename);

            //Reading each line of input from fileContents
            String fileContents;

            //Skip the first line of file
            bufReader.readLine();

            //Reading from file
            while ((fileContents = bufReader.readLine()) != null) {
                String[] transactionData = fileContents.split("\\|");

                //To store values from fileContents and assigning their values to transaction variables
                LocalDate transactionDate = LocalDate.parse(transactionData[0]);
                LocalTime transactionTime = LocalTime.parse(transactionData[1]);
                String transactionDesc = transactionData[2];
                String associatedVendor = transactionData[3];
                double transactionAmt = Double.parseDouble(transactionData[4]);

                //Creating a new transaction object, passing transaction variables to constructor
                t = new Transaction(transactionDate, transactionTime, transactionDesc, associatedVendor, transactionAmt);

                //Add each transaction to transaction ArrayList
                transactions.add(t);
            }

            //Successfully read file message
            System.out.println("File was successfully read!");

            //Closing bufReader
            bufReader.close();

            //Return transactions
            return transactions;

        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    //To retrieve current date and time for a transaction
    private static String getTransactionDateTime(LocalDateTime transactionDateTime) {

        DateTimeFormatter traditionalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String transactionDate = transactionDateTime.format(traditionalDate);

        DateTimeFormatter traditionalTime = DateTimeFormatter.ofPattern("HH:mm");
        String transactionTime = transactionDateTime.format(traditionalTime);

        return transactionDate + "|" + transactionTime;
    }

    //To retrieve a BufferedWriter
    private static BufferedWriter getBufferedWriter(String filename) throws IOException {
        BufferedWriter bufWriter = new BufferedWriter(new FileWriter(filename));
        return bufWriter;
    }

    //Initializing the BufferedReader
    private static BufferedReader openFileReader(String filename) throws FileNotFoundException {
        //Creating a new BufferedReader object to read "products.csv" and initializing it to read contents from FileReader
        BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        return bufReader;
    }
}
