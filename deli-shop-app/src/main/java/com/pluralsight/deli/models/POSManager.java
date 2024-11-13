package com.pluralsight.deli.models;

import com.pluralsight.deli.options.PaymentOption;
import com.pluralsight.deli.services.DateTimeHandler;
import com.pluralsight.deli.services.FileHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class POSManager {
    private static final LocalDateTime orderDateTime = LocalDateTime.now();
    private static String fileTimestamp = DateTimeHandler.getFileTimeStamp(orderDateTime);
    private static String receiptsFilePath = "src/main/resources/receipts/" + fileTimestamp + ".txt";

    public static void saveOrder(Order o, double total, PaymentOption payMethod, double changeDue) {
        try {
            List<OrderItem> items = o.getOrderItems();
            double amountPaid = payMethod.getDollars();
            BufferedWriter bufWriter = FileHandler.getBufferedWriter(receiptsFilePath);
            String[] receiptDateTimeFormat = DateTimeHandler.getReceiptDateTime(orderDateTime).split("\\|");

            bufWriter.write("***************** DELI-SHOP *****************\n");
            bufWriter.write("Date: " + String.format("%-15s", receiptDateTimeFormat[0]) + "Time: " + String.format("%-12s\n", receiptDateTimeFormat[1]));
            bufWriter.write("---------------------------------------------\n");

            for (OrderItem i : items) {
                //Prints every order item in the order
                bufWriter.write(i.printToReceipt());
            }

            bufWriter.write("---------------------------------------------\n");
            bufWriter.write("Sales Total: $" + String.format("%.2f\n", total));
            bufWriter.write("Pay method: " + String.format("%s", payMethod) + "Amount Paid: $" + String.format("%.2f\n", amountPaid));
            bufWriter.write("Change Due: $" + String.format("%.2f\n", changeDue));
            bufWriter.write("---------------------------------------------\n");
            bufWriter.write("Thank you for choosing the DELI-SHOP! \nHave a nice day :)");

            //Removes all order items for the next order
            o.removeAllOrderItems(items);

            bufWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //GET Endpoint to retrieve order details (/ORDER_DETAILS)
    public void getOrderDetails() {

    }

    //Printing to the user terminal or web front end interface
    public void printOrderReceipt() {

    }
}
