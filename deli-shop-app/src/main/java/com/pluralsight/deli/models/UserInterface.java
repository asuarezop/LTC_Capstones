package com.pluralsight.deli.models;

import JavaHelpers.ColorCodes;
import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.PremiumTopping;
import com.pluralsight.deli.options.RegularTopping;
import com.pluralsight.deli.options.SandwichSize;
import com.pluralsight.deli.services.MenuPromptHandler;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    //Related to input from user
    static String userInput;

    //Hold responses for adding more toppings and toasting sandwich
    static String response;

    //Initializing scanner to read from terminal input
    static Scanner inputSc = new Scanner(System.in);

    //Boolean condition to exit application screens
    static boolean exitApp = false;

    //Instance variable to hold a new order
    private Order blankOrder;

    //Initializing blankOrder with a new Order object instance
    private void init() {
        this.blankOrder = new Order();
    }

    public void showHomeScreen() throws IOException {
        do {
            init();
            System.out.println(MenuPromptHandler.homeScreenMenuHeader);
            System.out.println(MenuPromptHandler.homePrompt);
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
        do {
            System.out.println(MenuPromptHandler.orderScreenMenuHeader);
            System.out.println(MenuPromptHandler.orderPrompt);
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
        System.out.println(MenuPromptHandler.sandwichScreenMenuHeader);

        //Prompting user for sandwich bread type
        promptInstructions("Enter type of bread:  ");
        System.out.println(MenuPromptHandler.breadTypeOptions);
        BreadType sandwichBread = BreadType.valueOf(promptMenuSelection("Bread: "));

        //Prompting user for sandwich size
        promptInstructions("Enter sandwich size:  ");
        System.out.println(MenuPromptHandler.sandwichSizeOptions);
        SandwichSize size = SandwichSize.valueOfSize(promptMenuSelection("Size: "));

        boolean finished = false;
        do {
            //Initializing a new sandwich object
            Sandwich customerSandwich = new Sandwich();

            //Prompting user for regular sandwich toppings
            promptInstructions("Enter sandwich toppings:  ");
            System.out.println(MenuPromptHandler.sandwichRegularToppings);
            RegularTopping sandwichVeggies = RegularTopping.valueOf(promptMenuSelection("Toppings: "));

            //Adding regular toppings user selected onto sandwich
            Sandwich.addRegToppings(sandwichVeggies);

            //Viewing current toppings on sandwich
            List<Topping> appliedToppings = customerSandwich.getToppings();
            System.out.println(appliedToppings);

            promptInstructions("Would you like to add more toppings?:  ");
            response = promptUser(MenuPromptHandler.simpleResponse);

            if (response.equalsIgnoreCase("2")) {

                //Prompting user for premium sandwich toppings
                promptInstructions("Enter any premium toppings to add onto sandwich:  ");
                System.out.println(MenuPromptHandler.sandwichPremiumMeatToppings);
                PremiumTopping meats = PremiumTopping.valueOf(promptMenuSelection("Meat: "));
                System.out.println(MenuPromptHandler.sandwichPremiumCheeseToppings);
                PremiumTopping cheeses = PremiumTopping.valueOf(promptMenuSelection("Cheese: "));
                Sandwich.addPremToppings(meats);
                Sandwich.addPremToppings(cheeses);

                List<Topping> appliedPremToppings = customerSandwich.getToppings();
                System.out.println(appliedPremToppings);

                promptInstructions("Would you like to add more premium toppings?:  ");
                String addAdditionalPremToppings = promptUser(MenuPromptHandler.simpleResponse);

                if (addAdditionalPremToppings.equalsIgnoreCase("2")) {
                    promptInstructions("Would you like your sandwich toasted?:  ");
                    response = promptUser(MenuPromptHandler.simpleResponse);

                    if (response.equalsIgnoreCase("1")) {
                        customerSandwich = new Sandwich(size, sandwichBread, true);
                    } else {
                        customerSandwich = new Sandwich(size, sandwichBread, false);
                    }
                    //Adding customer sandwich item to order
                    blankOrder.addToOrder(customerSandwich);

                    //View finished sandwich
                    String completedSandwich = customerSandwich.displayItem();
                    System.out.println(completedSandwich);

                    System.out.println(customerSandwich.getPrice());

                    finished = true;
                }
            }
        } while (!finished);
    }

    //Retrieves user input from a prompt
    public String promptUser(String prompt) {
        System.out.print(ColorCodes.WHITE + prompt + ColorCodes.RESET);
        return userInput = inputSc.nextLine().trim();
    }

    public String promptMenuSelection(String prompt) {
        System.out.print(ColorCodes.WHITE + prompt + ColorCodes.RESET);
        return userInput = inputSc.nextLine().toUpperCase().trim();
    }

    public void promptInstructions(String prompt) {
        String[] textDetails = prompt.split(": ");
        System.out.println(ColorCodes.LIGHT_BLUE + textDetails[0] + ColorCodes.ORANGE_BOLD + ColorCodes.ITALIC + textDetails[1] + ColorCodes.RESET);
    }
}
