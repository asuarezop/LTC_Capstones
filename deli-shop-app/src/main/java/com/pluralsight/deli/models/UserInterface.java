package com.pluralsight.deli.models;

import helpers.ColorCodes;
import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.SandwichSize;
import com.pluralsight.deli.options.RegularTopping;
import com.pluralsight.deli.options.PremiumTopping;
import com.pluralsight.deli.services.MenuPromptHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    //Related to input from user
    static String userInput;

    //To hold user choice selections for order prompts
    static String userChoice;

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

    public void showHomeScreen() {
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
        } while (!exitApp);
    }

    public void processAddSandwichRequest() {
        System.out.println(MenuPromptHandler.sandwichScreenMenuHeader);

        BreadType sandwichBread = promptBreadType();
        SandwichSize size = promptSandwichSize();

        //Instantiating a new sandwich
        Sandwich customerSandwich = new Sandwich(size, sandwichBread);
        boolean finished;

        do {
            promptAddToppings(customerSandwich);

            //Viewing regular toppings on sandwich
            List<Topping> addedToppings = customerSandwich.getToppings();
            System.out.println(addedToppings);

            promptAddPremToppings(customerSandwich);

            //Viewing premium toppings on sandwich
            List<Topping> appliedPremToppings = customerSandwich.getToppings();
            System.out.println(appliedPremToppings);

            //Setting sandwich to toasted based on user choice
            boolean sandwichToasted = promptToasted();
            customerSandwich.setToasted(sandwichToasted);

            //Adding customer sandwich item to order
            blankOrder.addToOrder(customerSandwich);

            //View finished sandwich
            String completedSandwich = customerSandwich.displayItem();
            System.out.println(completedSandwich);

            finished = true;
        } while (!finished);
    }

    private BreadType promptBreadType() {
        promptInstructions("Enter type of bread:  ");
        System.out.println(MenuPromptHandler.breadTypeOptions);
        return BreadType.valueOf(promptMenuSelection("Bread: "));
    }

    private SandwichSize promptSandwichSize() {
        promptInstructions("Enter sandwich size:  ");
        System.out.println(MenuPromptHandler.sandwichSizeOptions);
        return SandwichSize.valueOfSize(promptMenuSelection("Size: "));
    }

    private void promptAddToppings(Sandwich s) {
        List<Topping> regularToppings = new ArrayList<>();

        do {
            //Prompting user for regular sandwich toppings
            promptInstructions("Enter sandwich toppings:  ");
            System.out.println(MenuPromptHandler.sandwichRegularToppings);
            RegularTopping sandwichVeggies = RegularTopping.valueOf(promptMenuSelection("Toppings: "));

            regularToppings.add(sandwichVeggies);

            promptInstructions("Would you like to add more toppings?:  ");
            userChoice = promptUser(MenuPromptHandler.simpleResponse);
        } while (!userChoice.equalsIgnoreCase("2"));

        s.addToppings(regularToppings);
    }

    private void promptAddPremToppings(Sandwich s) {
        List<Topping> premiumToppings = new ArrayList<>();

        do {
            //Prompting user for premium sandwich toppings
            promptInstructions("Enter any premium toppings to add onto sandwich:  ");

            System.out.println(MenuPromptHandler.sandwichPremiumMeatToppings);
            PremiumTopping meats = PremiumTopping.valueOf(promptMenuSelection("Meat: "));

            System.out.println(MenuPromptHandler.sandwichPremiumCheeseToppings);
            PremiumTopping cheeses = PremiumTopping.valueOf(promptMenuSelection("Cheese: "));

            premiumToppings.add(meats);
            premiumToppings.add(cheeses);

            promptInstructions("Would you like to add more premium toppings?:  ");
            userChoice = promptUser(MenuPromptHandler.simpleResponse);
        } while (!userChoice.equalsIgnoreCase("2"));

        s.addToppings(premiumToppings);
    }

    private boolean promptToasted() {
        promptInstructions("Would you like your sandwich toasted?:  ");
        userChoice = promptUser(MenuPromptHandler.simpleResponse);

        return userChoice.equalsIgnoreCase("Yes");
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
