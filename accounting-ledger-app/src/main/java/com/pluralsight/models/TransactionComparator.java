package com.pluralsight.models;

import java.util.Comparator;

/* CHILD CLASS (TransactionComparator)
   Purpose: Compares transaction LocalDateTimes between two transaction objects
        Implements means --> TransactionComparator will inherit the same properties
                             as Transaction class
 */
public class TransactionComparator implements Comparator<Transaction> {

    //Comparator has built-in compare() method to compare against two objects
    @Override
    public int compare(Transaction t1, Transaction t2) {

        if (t1.getDateOfTransaction() == t2.getDateOfTransaction()) {
            return 0;
        } else if (t1.getDateOfTransaction().isAfter(t2.getDateOfTransaction())) {
            return 1;
        } else {
            return -1;
        }
    }
}
