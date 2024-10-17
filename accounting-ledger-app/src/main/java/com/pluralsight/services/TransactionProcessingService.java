package com.pluralsight.services;

import com.pluralsight.ledger.LedgerApp;
import com.pluralsight.models.Transaction;

import java.io.IOException;
import java.time.*;

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

    //Filter through ledger by month to date ==> current date from month I am in now to the first of that month
    public static void monthToDateTransactionSearch() {
        //No need to ask user for input, that's for custom search
//        int startMonth = Integer.parseInt(PrintScreenService.promptUser("Enter the start month of your transaction (range from 1 - 12): "));
//        int endMonth = Integer.parseInt(PrintScreenService.promptUser("Enter the end month of your transaction (range from 1 - 12): "));
//        LocalDateTime firstOfTheMonth = LedgerApp.transactionDateTime.withDayOfMonth(1);

//        if (startMonth != 0 && endMonth != 0) {
            for (Transaction t: LedgerApp.ledger) {
                //If current transaction falls in the range of startMonth and endMonth, print out to console
//                if (t.getDateOfTransaction().getMonthValue() >= startMonth && t.getDateOfTransaction().getMonthValue() <= endMonth) {
//                    System.out.println("Date:" + t.getDateOfTransaction() + " Time:" + t.getTimeOfTransaction() + " Description:" + t.getTransactionDesc() + " Vendor:" + t.getVendor() + " Amount:" + t.getAmount());
//                }

                //If current transaction month is equal to the current month of LocalTime.now
                if (t.getDateOfTransaction().getMonth().equals(LedgerApp.transactionDateTime.getMonth())) {
                    System.out.println("Date:" + t.getDateOfTransaction() + " Time:" + t.getTimeOfTransaction() + " Description:" + t.getTransactionDesc() + " Vendor:" + t.getVendor() + " Amount:" + t.getAmount());
                }
            }
//        }
    }

    //Filter through ledger by comparing latest transactions to transactions made in the previous month
    public static void previousMonthTransactionSearch() {
        //Retrieving the date/time from LocalDateTime.now(), converting to LocalDate, and getting the month prior to latest transaction
        int lastMonth = LedgerApp.transactionDateTime.toLocalDate().minusMonths(1).getMonthValue();

        for (Transaction t: LedgerApp.ledger) {
            //If current transaction month is equal to last month
            if (t.getDateOfTransaction().getMonthValue() == lastMonth) {
                System.out.println("Date:" + t.getDateOfTransaction() + " Time:" + t.getTimeOfTransaction() + " Description:" + t.getTransactionDesc() + " Vendor:" + t.getVendor() + " Amount:" + t.getAmount());
            }
        }
    }

    //Start from Jan 1 to the latest date
    public static void yearToDateTransactionSearch() {
        LocalDate yearStartDate = LocalDate.of(LedgerApp.transactionDateTime.getYear(), Month.JANUARY, Month.JANUARY.firstDayOfYear(true));

        for (Transaction t: LedgerApp.ledger) {
            //If the current transaction year falls in the range of Jan. 1 to current date, print to the console
            if (t.getDateOfTransaction().isAfter(yearStartDate) && t.getDateOfTransaction().isBefore(t.getDateOfTransaction())) {
                System.out.println("Date:" + t.getDateOfTransaction() + " Time:" + t.getTimeOfTransaction() + " Description:" + t.getTransactionDesc() + " Vendor:" + t.getVendor() + " Amount:" + t.getAmount());
            }
        }
    }

    //Show only transactions from the previous year
    public static void previousYearTransactionSearch() {
        //Get the current year value from LocalDateTime.now, convert to LocalDate, and get the previous year
        int lastYear = LedgerApp.transactionDateTime.toLocalDate().minusYears(1).getYear();

        for (Transaction t: LedgerApp.ledger) {
            //If transaction year matches the previous year
            if (t.getDateOfTransaction().getYear() == lastYear) {
                System.out.println("Date:" + t.getDateOfTransaction() + " Time:" + t.getTimeOfTransaction() + " Description:" + t.getTransactionDesc() + " Vendor:" + t.getVendor() + " Amount:" + t.getAmount());
            }
        }
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
