package com.pluralsight.models;

import com.pluralsight.ledger.LedgerApp;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate dateOfTransaction;
    private LocalTime timeOfTransaction;
    private String transactionDesc;
    private String vendor;
    private double amount;

    public Transaction(LocalDate dateOfTransaction, LocalTime timeOfTransaction, String transactionDesc, String vendor, double amount) {
        this.dateOfTransaction = dateOfTransaction;
        this.timeOfTransaction = timeOfTransaction;
        this.transactionDesc = transactionDesc;
        this.vendor = vendor;
        this.amount = amount;
    }

    //Getters
    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }
    public LocalTime getTimeOfTransaction() {
        return timeOfTransaction;
    }
    public String getTransactionDesc() {
        return transactionDesc;
    }
    public String getVendor() {
        return vendor;
    }
    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        //Variables to color text output
        String transactionDateColor = LedgerApp.escapeKey + "[38;5;220m";
        String transactionTimeColor = LedgerApp.escapeKey + "[38;5;51m";
        String transactionDescColor = LedgerApp.escapeKey + "[38;5;210m";
        String transactionVendorColor = LedgerApp.escapeKey + "[38;5;183m";
        String transactionAmountColorPos = LedgerApp.escapeKey + "[38;5;82m";
        String transactionAmountColorNeg = LedgerApp.escapeKey + "[38;5;196m";

        return transactionDateColor + "Date: " + dateOfTransaction +
                transactionTimeColor + " Time: " + timeOfTransaction +
                transactionDescColor + " Description: " + transactionDesc +
                transactionVendorColor + " Vendor: " + vendor +

                //Conditional rendering of amount text color based on whether value greater than 0
                (amount > 0 ? transactionAmountColorPos + " Amount: " + amount : transactionAmountColorNeg + " Amount: " + amount);
    }
}
