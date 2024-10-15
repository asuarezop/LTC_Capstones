package com.pluralsight.models;

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

}
