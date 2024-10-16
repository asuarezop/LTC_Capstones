package com.pluralsight.services;

import com.pluralsight.models.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileHandlerService {

    //To retrieve a BufferedWriter
    public static BufferedWriter getBufferedWriter(String filename) throws IOException {
        BufferedWriter bufWriter = new BufferedWriter(new FileWriter(filename, true)); //Set fileWriter to append mode in order to prevent data from being overwritten
        return bufWriter;
    }

    //Initializing the BufferedReader
    public static BufferedReader openFileReader(String filename) throws FileNotFoundException {
        //Creating a new BufferedReader object to read "products.csv" and initializing it to read contents from FileReader
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
}
