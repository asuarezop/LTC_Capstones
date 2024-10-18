package com.pluralsight.services;

import com.pluralsight.ledger.LedgerApp;
import com.pluralsight.models.Transaction;
import com.pluralsight.models.TransactionComparator;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class FileHandlerService {

    public static ArrayList<Transaction> newEntries = new ArrayList<>();
    public static String successActionColor = LedgerApp.escapeKey + "[38;5;46m";

    public static BufferedWriter getBufferedWriter(String filename) throws IOException {
        //Set fileWriter to append mode in order to prevent data from being overwritten
        BufferedWriter bufWriter = new BufferedWriter(new FileWriter(filename, true));
        return bufWriter;
    }

    //Initializing the BufferedReader
    public static BufferedReader openFileReader(String filename) throws FileNotFoundException {
        //Creating a new BufferedReader object to read file and initializing it to read contents from FileReader
        BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        return bufReader;
    }

    public static ArrayList<Transaction> readTransactionFile(String filename) {
        //Represents a single transaction to be added
        Transaction t;

        //Store a read-copy of transactions into ArrayList
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            //Calling openFileReader method to initialize BufferedReader
            BufferedReader bufReader = openFileReader(filename);

            //Reading each line of input from fileContents
            String fileContents;

            //Clear any existing items from previous write sessions
            transactions.clear();

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

                //Sorting transactions in descending order by comparing two transaction date times
                transactions.sort((t1, t2) -> t2.getDateOfTransaction().compareTo(t1.getDateOfTransaction()));
            }

            //Successfully read file message
            System.out.println(successActionColor + "File was successfully read!");

            //Closing bufReader
            bufReader.close();

            //Return transactions
            return transactions;

        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    public static void writeToTransactionFile(double transactionAmt, String transactionDesc, String vendorName) throws IOException {
        String[] transactionDateTimeFormat;
        Transaction t;

        //Call method to retrieve date and time
        transactionDateTimeFormat = DateTimeHandlerService.getTransactionDateTime(LedgerApp.transactionDateTime).split("\\|");

        LedgerApp.bufWriter = getBufferedWriter(LedgerApp.transactionsFilePath);

        if(!PrintScreenService.userInput.isEmpty()) {
            t = new Transaction(LocalDate.parse(transactionDateTimeFormat[0]), LocalTime.parse(transactionDateTimeFormat[1]), transactionDesc, vendorName, transactionAmt);

            LedgerApp.bufWriter.write(t.getDateOfTransaction() + "|" + t.getTimeOfTransaction() + "|" + t.getTransactionDesc() + "|" + t.getVendor() + "|");
            LedgerApp.bufWriter.write(String.format("%.2f \n", t.getAmount()));

            //Adding to the original ledger and backup ledger copy
            LedgerApp.ledger.add(t);
            newEntries.add(t);
        }

        //Successfully written to file message
        System.out.println(successActionColor + "Transaction was successfully written to file!");

        LedgerApp.bufWriter.close();
    }
}
