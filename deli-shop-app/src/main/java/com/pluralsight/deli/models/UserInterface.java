package com.pluralsight.deli.models;

import com.pluralsight.deli.enums.*;
import com.pluralsight.deli.interfaces.OrderItem;
import com.pluralsight.deli.interfaces.Topping;
import com.pluralsight.deli.services.UIProcessingHandler;
import com.pluralsight.deli.services.MenuPromptHandler;
import helpers.ColorCodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static UIProcessingHandler uiProcessor;

    //Initializing UIProcessor variables
    private void init() {
        uiProcessor = new UIProcessingHandler();
        uiProcessor.blankOrder = new Order();
        uiProcessor.inputSc = new Scanner(System.in);
        uiProcessor.exitApp = false;
    }

    public void showHomeScreen() {
        do {
            init();
            System.out.println(ColorCodes.LIGHT_BLUE + MenuPromptHandler.homeScreenMenuHeader + ColorCodes.RESET);
            System.out.println(MenuPromptHandler.homePrompt);
            uiProcessor.userInput = uiProcessor.inputSc.nextLine().trim().toUpperCase();

            switch (uiProcessor.userInput) {
                case "1":
                    uiProcessor.processNewOrderRequest();
                    break;
                case "X":
                    uiProcessor.exitApp = true;
                    break;
                default:
                    System.out.println("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!uiProcessor.exitApp);
    }

    public static BreadType promptBreadType() {
        promptInstructions("Enter type of bread:  ");
        System.out.println(MenuPromptHandler.breadTypeOptions);
        return BreadType.valueFromChoice(promptMenuSelection("Bread: "));
    }

    public static SandwichSize promptSandwichSize() {
        promptInstructions("Enter sandwich size:  ");
        System.out.println(MenuPromptHandler.sandwichSizeOptions);
        return SandwichSize.valueFromChoice(promptMenuSelection("Size: "));
    }

    public static SauceType promptSandwichSpread() {
        promptInstructions("Enter sandwich spread:  ");
        System.out.println(MenuPromptHandler.sauceOptions);
        return SauceType.valueFromChoice(promptMenuSelection("Spread: "));
    }

    public static void promptAddToppings(Sandwich s) {
        List<Topping> regularToppings = new ArrayList<>();

        do {
            //Prompting user for regular sandwich toppings
            promptInstructions("Enter sandwich toppings:  ");
            System.out.println(MenuPromptHandler.sandwichRegularToppings);
            RegularTopping sandwichVeggies = RegularTopping.valueFromChoice(promptMenuSelection("Toppings: "));
            System.out.println(sandwichVeggies);

            regularToppings.add(sandwichVeggies);

            promptInstructions("Would you like to add more toppings?:  ");
            uiProcessor.userChoice = promptUser(MenuPromptHandler.simpleResponse);
        } while (! uiProcessor.userChoice.equalsIgnoreCase("2"));

        s.addToppings(regularToppings);
    }

    public static void promptAddPremToppings(Sandwich s) {
        List<Topping> premiumToppings = new ArrayList<>();

        do {
            //Prompting user for premium sandwich toppings
            promptInstructions("Enter any premium toppings to add onto sandwich:  ");

            System.out.println(MenuPromptHandler.sandwichPremiumMeatToppings);
            PremiumTopping meats = PremiumTopping.valueFromChoice(promptMenuSelection("Meat: "));

            System.out.println(MenuPromptHandler.sandwichPremiumCheeseToppings);
            PremiumTopping cheeses = PremiumTopping.valueFromChoice(promptMenuSelection("Cheese: "));

            premiumToppings.add(meats);
            premiumToppings.add(cheeses);

            promptInstructions("Would you like to add more premium toppings?:  ");
            uiProcessor.userChoice = promptUser(MenuPromptHandler.simpleResponse);

            if ( uiProcessor.userChoice.equals("1")) {
                s.setExtraToppings(true);
            }
        } while (! uiProcessor.userChoice.equalsIgnoreCase("2"));

        s.addToppings(premiumToppings);
    }

    public static boolean promptToasted() {
        promptInstructions("Would you like your sandwich toasted?:  ");
        uiProcessor.userChoice = promptUser(MenuPromptHandler.simpleResponse);
        return  uiProcessor.userChoice.equalsIgnoreCase("1");
    }

    public static DrinkSize promptDrinkSize() {
        promptInstructions("What size drink would you like?:  ");
        System.out.println(MenuPromptHandler.drinkSizeOptions);
        return DrinkSize.valueFromChoice(promptMenuSelection("Size: "));
    }

    public static DrinkType promptDrinkType() {
        promptInstructions("What type of drink would you like?:  ");
        System.out.println(MenuPromptHandler.drinkTypeOptions);
        return DrinkType.valueFromChoice(promptMenuSelection("Type: "));
    }

    public static DrinkFlavor promptDrinkFlavor() {
        promptInstructions("What flavored drink would you like?:  ");
        System.out.println(MenuPromptHandler.drinkFlavorOptions);
        return DrinkFlavor.valueFromChoice(promptMenuSelection("Flavor: "));
    }

    public static ChipFlavor promptChipsFlavor() {
        promptInstructions("What chips would you like?:  ");
        System.out.println(MenuPromptHandler.chipsFlavorOptions);
        return ChipFlavor.valueFromChoice(promptMenuSelection("Flavor: "));
    }

    public static void promptSauces() {
        do {
            promptInstructions("Any sauces you like to add alongside your order?:  ");
            System.out.println(MenuPromptHandler.sauceOptions);
            SauceType sauce = SauceType.valueFromChoice(promptMenuSelection("Sauce: "));

            uiProcessor.blankOrder.addToOrder(sauce);

            promptInstructions("Would you like to include additional sauces?:  ");
            uiProcessor.userChoice = promptUser(MenuPromptHandler.simpleResponse);
        } while (!uiProcessor.userChoice.equalsIgnoreCase("2"));
    }

    public static void promptFinalizeOrder() {
        List<OrderItem> items = uiProcessor.retrieveAllOrderItems();

        System.out.println(ColorCodes.CYAN + MenuPromptHandler.orderDetailsScreenHeader + ColorCodes.RESET);

        //Printing all order items by category
        uiProcessor.printIndividualOrderItems(items);

        //Calculating total price of every item on order and printing it out
        double orderTotal = uiProcessor.blankOrder.getTotalCost(items);
        String printedTotal = String.format("Order total: $%.2f", orderTotal);
        System.out.println(ColorCodes.CYAN + printedTotal + ColorCodes.RESET);

        //Confirm with user if order is correct
        boolean confirmation = promptOrderConfirmation();

        if (confirmation) {
            //Prompting user for payment method
            PaymentOption payMethod = promptForPayment();
            System.out.println(payMethod);

            //Getting change due for order payment
            double changeForOrder = promptChangeOwed(orderTotal, payMethod);

            //Save order details
            POSManager.saveOrder(uiProcessor.blankOrder, orderTotal, payMethod, changeForOrder);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Order was successfully printed!" + ColorCodes.RESET);
        } else {
            //Prompt for user if order details was incorrect
            System.out.println(ColorCodes.RED + MenuPromptHandler.cancelScreenHeader + ColorCodes.RESET);
            promptInstructions("Would you like to redo the order?:  ");
            uiProcessor.userChoice = promptUser(MenuPromptHandler.simpleResponse);

            //Cancel order if user selected "Yes"
            if ( uiProcessor.userChoice.equals("1")) uiProcessor.processCancelOrderRequest();
        }
    }

    protected static PaymentOption promptForPayment() {
        System.out.println(ColorCodes.WHITE + MenuPromptHandler.paymentScreenHeader + ColorCodes.RESET);
        promptInstructions("How would you like to pay?:  ");
        System.out.println(MenuPromptHandler.paymentOptions);
        return PaymentOption.valueFromChoice(promptMenuSelection("Payment Method: "));
    }

    protected static double promptChangeOwed(double orderTotal, PaymentOption payMethod) {
        //Retrieving money amount based on payment option
        double moneyForOrder = payMethod.getDollars();

        //To hold change from order transaction
        double changeDue = 0.0;

        if (moneyForOrder >= orderTotal) {
            changeDue += uiProcessor.calculateChangeDue(moneyForOrder, orderTotal);
        } else {
            System.out.println("Pay amount is insufficient to cover cost of total order.");
        }

        return changeDue;
    }

    protected static boolean promptOrderConfirmation() {
        System.out.println(ColorCodes.GREEN + MenuPromptHandler.orderConfirmationScreenHeader + ColorCodes.RESET);
        promptInstructions("Please confirm order details is correct:  ");
        uiProcessor.userChoice = promptUser(MenuPromptHandler.simpleResponse);

        //Returns true if user selected "Yes"
        return uiProcessor.userChoice.equals("1");
    }

    //Methods to retrieve user input and display prompts to user
    public static String promptUser(String prompt) {
        System.out.print(ColorCodes.WHITE + prompt + ColorCodes.RESET);
        return uiProcessor.userInput =  uiProcessor.inputSc.nextLine().trim();
    }

    public static String promptMenuSelection(String prompt) {
        System.out.print(ColorCodes.WHITE + prompt + ColorCodes.RESET);
        return uiProcessor.userInput =  uiProcessor.inputSc.nextLine().toUpperCase().trim();
    }

    public static void promptInstructions(String prompt) {
        String[] textDetails = prompt.split(": ");
        System.out.println(ColorCodes.LIGHT_BLUE + textDetails[0] + ColorCodes.ORANGE_BOLD + ColorCodes.ITALIC + textDetails[1] + ColorCodes.RESET);
    }
}
