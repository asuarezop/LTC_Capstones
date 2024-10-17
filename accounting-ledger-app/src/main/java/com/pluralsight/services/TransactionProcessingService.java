package com.pluralsight.services;

import com.pluralsight.ledger.LedgerApp;
import com.pluralsight.models.Transaction;

import java.io.IOException;
import java.time.*;

public class TransactionProcessingService {

    //Handles adding transaction entries to ledger (Deposits and Payments)
    public static void addTransaction(String userInput) throws IOException {
        String transactionDesc;
        String vendorName;

        //User selected to Add Deposit
        if (userInput.equals("D") || userInput.equals("d")) {
            double transactionDepositAmt;

            //Parsing string to double from PrintScreenService.promptUser()
            transactionDepositAmt = Double.parseDouble(PrintScreenService.promptUser("Enter the deposit amount from the transaction: "));
            transactionDesc = PrintScreenService.promptUser("Enter the transaction description: ");
            vendorName = PrintScreenService.promptUser("Enter the vendor name from the transaction: ");

            //Passing values to be written to transactions.csv file
            FileHandlerService.writeToTransactionFile(transactionDepositAmt, transactionDesc, vendorName);
        }
        //User selected to Make Payment
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

    //Prints transactions from ledger
    public static void showTransactionsFromLedger(String userInput) {
        switch (userInput) {
            case "A", "a":
                //User selected to Display All Entries
                for (Transaction t : LedgerApp.ledger) {
                    PrintScreenService.printTransaction(t);
                }
                break;

            case "D", "d":
                //User selected to Show Deposits only (positive transactions)
                for (Transaction t : LedgerApp.ledger) {
                    //If transaction amount is not negative
                    if (t.getAmount() > 0) {
                        PrintScreenService.printTransaction(t);
                    }
                }
                break;

            case "P", "p":
                //User selected to show Payments only (negative transactions)
                for (Transaction t : LedgerApp.ledger) {
                    //If amount is not positive (in the negative range)
                    if (t.getAmount() < 0) {
                        PrintScreenService.printTransaction(t);
                    }
                }
                break;
        }
    }

    //Filter ledger by current month to latest date
    public static void monthToDateTransactionSearch() {
        //Variables to store current year and month from LocalDateTime.now()
        int currentYear = LedgerApp.transactionDateTime.getYear();
        Month currentMonth = LedgerApp.transactionDateTime.getMonth();

        //Creating a new LocalDate that is the first day of current month - yyyy-MM-01
        LocalDate monthStartDate = LocalDate.of(currentYear, currentMonth, 1);

        for (Transaction t : LedgerApp.ledger) {
            //If current transaction month is after monthStartDate (1st day of current month), print to console
            if (t.getDateOfTransaction().isAfter(monthStartDate)) {
                PrintScreenService.printTransaction(t);
            }
        }
    }

    //Filter ledger by current month transactions to transactions made in the previous month
    public static void previousMonthTransactionSearch() {
        //Retrieving the date/time from LocalDateTime.now(), converting to LocalDate, and getting the month prior to latest transaction
        int lastMonth = LedgerApp.transactionDateTime.toLocalDate().minusMonths(1).getMonthValue();

        for (Transaction t : LedgerApp.ledger) {
            //If current transaction month is equal to last month
            if (t.getDateOfTransaction().getMonthValue() == lastMonth) {
                PrintScreenService.printTransaction(t);
            }
        }
    }

    //Show transactions that fall between Jan 1 to the latest transaction date
    public static void yearToDateTransactionSearch() {
        //Variable to store current year from LocalDateTime.now()
        int currentYear = LedgerApp.transactionDateTime.getYear();

        //Get the first month and first day of the current year - yyyy-01-01
        LocalDate yearStartDate = LocalDate.of(currentYear, Month.JANUARY, Month.JANUARY.firstDayOfYear(true));

        for (Transaction t : LedgerApp.ledger) {
            //If the current transaction is after yearStartDate (Jan 1st), print to the console
            if (t.getDateOfTransaction().isAfter(yearStartDate)) {
               PrintScreenService.printTransaction(t);
            }
        }
    }

    //Show only transactions from the previous year
    public static void previousYearTransactionSearch() {
        //Get the current year value from LocalDateTime.now, convert to LocalDate, and get the previous year
        int lastYear = LedgerApp.transactionDateTime.toLocalDate().minusYears(1).getYear();

        for (Transaction t : LedgerApp.ledger) {
            //If transaction year matches the previous year, print to transaction to the console
            if (t.getDateOfTransaction().getYear() == lastYear) {
             PrintScreenService.printTransaction(t);
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
                   PrintScreenService.printTransaction(t);
                }
            }
        }
    }

    //Custom Search
    public static void searchTransactionByStartEndDate() {
        int startMonth = Integer.parseInt(PrintScreenService.promptUser("Enter the start month of your transaction (range from 1 - 12): "));
        int endMonth = Integer.parseInt(PrintScreenService.promptUser("Enter the end month of your transaction (range from 1 - 12): "));

        if (startMonth != 0 && endMonth != 0) {
            for (Transaction t : LedgerApp.ledger) {
                //If current transaction falls in the range of startMonth and endMonth, print out to console
                if (t.getDateOfTransaction().getMonthValue() >= startMonth && t.getDateOfTransaction().getMonthValue() <= endMonth) {
                    PrintScreenService.printTransaction(t);
                }
            }
        }
    }
}
