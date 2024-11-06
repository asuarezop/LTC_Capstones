package com.pluralsight.deli.models;

import JavaHelpers.ColorCodes;
import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.SandwichSize;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    //Related to input from user
    static String userInput;

    //Initializing scanner to read from terminal input
    static Scanner inputSc = new Scanner(System.in);

    //Boolean condition to exit application screens
    static boolean exitApp = false;

    public void showHomeScreen() throws IOException {
        String homeScreenMenuHeader = """
                =================================
                |        DELI SHOP (HOME)       |
                =================================
                """;
        String prompt = """
                Please make your selection:
                
                [1] New Order
                [X] Exit Application
                """;

        do {
            System.out.println(homeScreenMenuHeader);
            System.out.println(prompt);
            userInput = inputSc.nextLine().trim().toUpperCase();

            switch (userInput) {
                case "1":
                    processNewOrderRequest();
                    break;
                case "X":
                    exitApp = true;
                    break;
                default:
                    System.out.println("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!exitApp);
    }

    public void processNewOrderRequest() {
        String orderScreenMenuHeader = """
                =================================
                |             ORDER             |
                =================================
                """;
        String orderPrompt = """
                Select from the following options:
                
                1) Add Sandwich
                2) Add Drink
                3) Add Chips
                4) Checkout
                0) Cancel Order
                """;

        do {
            System.out.println(orderScreenMenuHeader);
            System.out.println(orderPrompt);
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    processAddSandwichRequest();
                    break;
                case "2":
                    //ProcessDrinkRequest
                    break;
                case "3":
                    //ProcessAddChipsRequest
                    break;
                case "4":
                    //ProcessCheckoutRequest
                    break;
                case "0":
                    //Remove current order and go back to the Home Screen
                    break;
                default:
                    System.out.println("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!exitApp);
    }

    public void processAddSandwichRequest() {
        String sandwichScreenMenuHeader = """
                =================================
                |        CREATE A SANDWICH      |
                =================================
                """;

        System.out.println(sandwichScreenMenuHeader);
        promptInstructions("Enter type of bread:  ");
        //TODO - display list of values for bread using lambda expression
        BreadType sandwichBread = BreadType.valueOf(promptUser("Bread: "));

        promptInstructions("Enter sandwich size:  ");
        SandwichSize size = SandwichSize.valueOf(promptUser("Size: "));

        promptInstructions("Enter sandwich toppings:  ");
//        Topping sandwichTopping =
    }

    //Retrieves user input from a prompt
    public String promptUser(String prompt) {
        System.out.print(ColorCodes.WHITE + prompt + ColorCodes.RESET);
        return userInput = inputSc.nextLine().trim();
    }

    public void promptInstructions(String prompt) {
        String[] textDetails = prompt.split(": ");
        System.out.println(ColorCodes.LIGHT_BLUE + textDetails[0] + ColorCodes.ORANGE_BOLD + ColorCodes.ITALIC + textDetails[1] + ColorCodes.RESET);
    }
}
