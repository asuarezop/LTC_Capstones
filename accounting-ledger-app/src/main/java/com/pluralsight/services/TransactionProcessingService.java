package com.pluralsight.services;

import com.pluralsight.ledger.LedgerApp;
import com.pluralsight.models.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionProcessingService {

    public static void addTransaction(String userInput) throws IOException {
        String transactionDesc;
        String vendorName;

        //User selected to Add Deposit transaction
        if (userInput.equals("D") || userInput.equals("d")) {
            double transactionDepositAmt;

            //Parsing string to double from PrintScreenService.promptUser()
            transactionDepositAmt = Double.parseDouble(PrintScreenService.promptUser("Enter the deposit amount from the transaction: "));
            transactionDesc = PrintScreenService.promptUser("Enter the transaction description: ");
            vendorName = PrintScreenService.promptUser("Enter the vendor name from the transaction: ");

            //Passing values to be written to transactions.csv file
            FileHandlerService.writeToTransactionFile(transactionDepositAmt, transactionDesc, vendorName);
        }
        //User selected to Make Payment transaction
        else if (userInput.equals("P") || userInput.equals("p")) {
            double transactionPaymentAmt;

            //Parsing string to double from PrintScreenService.promptUser() and multiplying by -1 to show as negative
            transactionPaymentAmt = Double.parseDouble(PrintScreenService.promptUser("Enter the deposit amount from the transaction: ")) * -1;
            transactionDesc = PrintScreenService.promptUser("Enter the transaction description: ");
            vendorName = PrintScreenService.promptUser("Enter the vendor name from the transaction: ");

            //Passing values to be written to transactions.csv file
            FileHandlerService.writeToTransactionFile(transactionPaymentAmt, transactionDesc, vendorName);
        }
    }

    public static void showTransactionsFromLedger(String userInput) {

        switch (userInput) {
            case "A", "a":
                //User selected to Display All Entries
                for (Transaction t : LedgerApp.ledger) {
                    System.out.println("Date:" + t.getDateOfTransaction() + " Time:" + t.getTimeOfTransaction() + " Description:" + t.getTransactionDesc() + " Vendor:" + t.getVendor() + " Amount:" + t.getAmount());
                }
                break;

            case "D", "d":
                //User selected to Show Deposits only (positive transactions)
                for (Transaction d : LedgerApp.ledger) {
                    //If transaction amount is not negative
                    if (d.getAmount() > 0) {
                        System.out.println("Date:" + d.getDateOfTransaction() + " Time:" + d.getTimeOfTransaction() + " Description:" + d.getTransactionDesc() + " Vendor:" + d.getVendor() + " Amount:" + d.getAmount());
                    }
                }
                break;

            case "P", "p":
                //User selected to show Payments only (negative transactions)
                for (Transaction p : LedgerApp.ledger) {
                    //If amount is not positive (in the negative range)
                    if (p.getAmount() < 0) {
                        System.out.println("Date:" + p.getDateOfTransaction() + " Time:" + p.getTimeOfTransaction() + " Description:" + p.getTransactionDesc() + " Vendor:" + p.getVendor() + " Amount:" + p.getAmount());
                    }
                }
                break;
        }
    }

    public static void monthToMonthTransactionSearch() {
        //Filter through ledger by startMonth and endMonth within the same month
        LocalDate startMonth = LocalDate.parse(PrintScreenService.promptUser("Enter the start month of your transaction: "));
        LocalDate endMonth = LocalDate.parse(PrintScreenService.promptUser("Enter the end month of your transaction: "));

//        if (!startMonth.isEmpty() && !endMonth.isEmpty()) {
//            for (Transaction t: LedgerApp.ledger) {
//                //Compare month value
////                if (startMonth.equals(t.getDateOfTransaction().getMonth()) {
////
////                }
//            }
//        }
    }

    public static void searchTransactionByVendor() {
        //Returns user search term
        String searchTerm = PrintScreenService.promptUser("Enter the vendor name you'd like to search from ledger: ");

        if (!searchTerm.isEmpty()) {
            for (Transaction t : LedgerApp.ledger) {
                //Filtering ledger to find all transactions that match vendor name and printing to the console
                if (searchTerm.equalsIgnoreCase(t.getVendor())) {
                    System.out.println("Date:" + t.getDateOfTransaction() + " Time:" + t.getTimeOfTransaction() + " Description:" + t.getTransactionDesc() + " Vendor:" + t.getVendor() + " Amount:" + t.getAmount());
                }
            }
        }
    }
}
