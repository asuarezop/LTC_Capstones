package com.pluralsight.deli.services;

import com.pluralsight.deli.enums.*;
import com.pluralsight.deli.interfaces.OrderItem;
import com.pluralsight.deli.interfaces.Topping;
import com.pluralsight.deli.models.*;
import helpers.ColorCodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIProcessingHandler {
    //Related to input from user
    public String userInput;

    //To hold user choice selections for order prompts
    public String userChoice;

    //Initializing scanner to read from terminal input
    public Scanner inputSc;

    //Boolean condition to exit application screens
    public boolean exitApp;

    //Instance variable to hold a new order
    public Order blankOrder;

    //Default constructor
    public UIProcessingHandler() {}

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
                    processAddDrinkRequest();
                    break;
                case "3":
                    processAddChipsRequest();
                    break;
                case "4":
                    processCheckoutRequest();
                    break;
                case "0":
                    processCancelOrderRequest();
                    break;
                default:
                    System.out.println("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!exitApp);
    }

    public void processAddSandwichRequest() {
        System.out.println(MenuPromptHandler.sandwichScreenMenuHeader);

        //Prompting to get sandwich size and type of bread
        BreadType sandwichBread = promptBreadType();
        System.out.println(sandwichBread);
        SandwichSize size = promptSandwichSize();

        //Instantiating a new sandwich
        Sandwich customerSandwich = new Sandwich(size, sandwichBread);
        boolean finished;

        do {
            //Prompt to add regular toppings to sandwich
            promptAddToppings(customerSandwich);

            //Viewing regular toppings on sandwich
            List<Topping> addedToppings = customerSandwich.getToppings();
            System.out.println(addedToppings);

            //Prompt to add premium toppings to sandwich
            promptAddPremToppings(customerSandwich);

            //Viewing premium toppings on sandwich
            List<Topping> appliedPremToppings = customerSandwich.getToppings();
            System.out.println(appliedPremToppings);

            //Setting sandwich to toasted based on user choice
            boolean sandwichToasted = promptToasted();
            customerSandwich.setToasted(sandwichToasted);

            //Adding customer sandwich item to order
            blankOrder.addToOrder(customerSandwich);

            //Exit loop
            finished = true;
        } while (!finished);
    }

    public void processAddDrinkRequest() {
        System.out.println(MenuPromptHandler.drinkScreenMenuHeader);

        //Getting size, type, and flavor of drink from user
        DrinkSize size = promptDrinkSize();
        System.out.println(size);
        DrinkType type = promptDrinkType();
        System.out.println(type);
        DrinkFlavor flavor = promptDrinkFlavor();
        System.out.println(flavor);

        //Instantiating a new drink
        Drink customerDrink = new Drink(size, type, flavor);

        //Adding customer drink item to order
        blankOrder.addToOrder(customerDrink);

        //Viewing customer's drink
        System.out.println(customerDrink.printToReceipt());
    }

    public void processAddChipsRequest() {
        System.out.println(MenuPromptHandler.chipsScreenMenuHeader);

        //Getting flavor of chips from user
        ChipFlavor flavor = promptChipsFlavor();

        //Instantiating a new bag of chips
        BagOfChips chips = new BagOfChips(flavor);

        //Adding chips item to order
        blankOrder.addToOrder(chips);

        //Viewing customer's chips
        System.out.println(chips.printToReceipt());
    }

    public void processCheckoutRequest() {
        System.out.println(MenuPromptHandler.checkoutScreenMenuHeader);

        //Prompting user to add any sauces to their order
        promptSauces();

        //View order details and total pricing for every order item
        promptFinalizeOrder();
    }

    public void processCancelOrderRequest() {
        List<OrderItem> items = retrieveAllOrderItems();
        blankOrder.removeAllOrderItems(items);
    }

    private BreadType promptBreadType() {
        promptInstructions("Enter type of bread:  ");
        System.out.println(MenuPromptHandler.breadTypeOptions);
        return BreadType.valueFromChoice(promptMenuSelection("Bread: "));
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
            RegularTopping sandwichVeggies = RegularTopping.valueFromChoice(promptMenuSelection("Toppings: "));

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
            PremiumTopping meats = PremiumTopping.valueFromChoice(promptMenuSelection("Meat: "));

            System.out.println(MenuPromptHandler.sandwichPremiumCheeseToppings);
            PremiumTopping cheeses = PremiumTopping.valueFromChoice(promptMenuSelection("Cheese: "));

            premiumToppings.add(meats);
            premiumToppings.add(cheeses);

            promptInstructions("Would you like to add more premium toppings?:  ");
            userChoice = promptUser(MenuPromptHandler.simpleResponse);

            if (userChoice.equals("1")) {
                s.setExtraToppings(true);
            }
        } while (!userChoice.equalsIgnoreCase("2"));

        s.addToppings(premiumToppings);
    }

    private boolean promptToasted() {
        promptInstructions("Would you like your sandwich toasted?:  ");
        userChoice = promptUser(MenuPromptHandler.simpleResponse);
        return userChoice.equalsIgnoreCase("1");
    }

    private DrinkSize promptDrinkSize() {
        promptInstructions("What size drink would you like?:  ");
        System.out.println(MenuPromptHandler.drinkSizeOptions);
        return DrinkSize.valueFromChoice(promptMenuSelection("Size: "));
    }

    private DrinkType promptDrinkType() {
        promptInstructions("What type of drink would you like?:  ");
        System.out.println(MenuPromptHandler.drinkTypeOptions);
        return DrinkType.valueFromChoice(promptMenuSelection("Type: "));
    }

    private DrinkFlavor promptDrinkFlavor() {
        promptInstructions("What flavored drink would you like?:  ");
        System.out.println(MenuPromptHandler.drinkFlavorOptions);
        return DrinkFlavor.valueFromChoice(promptMenuSelection("Flavor: "));
    }

    private ChipFlavor promptChipsFlavor() {
        promptInstructions("What chips would you like?:  ");
        System.out.println(MenuPromptHandler.chipsFlavorOptions);
        return ChipFlavor.valueFromChoice(promptMenuSelection("Flavor: "));
    }

    private void promptSauces() {
        do {
            promptInstructions("Any sauces you like to add alongside your order?:  ");
            System.out.println(MenuPromptHandler.sauceOptions);
            SauceType sauce = SauceType.valueFromChoice(promptMenuSelection("Sauce: "));

            blankOrder.addToOrder(sauce);

            promptInstructions("Would you like to include additional sauces?:  ");
            userChoice = promptUser(MenuPromptHandler.simpleResponse);
        } while (!userChoice.equalsIgnoreCase("2"));
    }

    private void promptFinalizeOrder() {
        //Retrieving list of all order items
        List<OrderItem> items = retrieveAllOrderItems();
        System.out.println(items);

        System.out.println(MenuPromptHandler.orderDetailsScreenHeader);

        //Checking all order items by category
        printIndividualOrderItems(items);

        //Calculating total price of every item on order and printing it out
        double orderTotal = blankOrder.getTotalCost(items);
        String printedTotal = String.format("Order total: $%.2f", orderTotal);
        System.out.println(printedTotal);

        //Confirm with user if order is correct
        boolean confirmation = promptOrderConfirmation();

        if (confirmation) {
            //Prompting user for payment method
            PaymentOption payMethod = promptForPayment();
            System.out.println(payMethod);

            //Getting change due for order payment
            double changeForOrder = promptChangeOwed(orderTotal, payMethod);

            //Save order details
            POSManager.saveOrder(blankOrder, orderTotal, payMethod, changeForOrder);
        } else {
            //Prompt for user if order details was incorrect
            System.out.println(MenuPromptHandler.cancelScreenHeader);
            promptInstructions("Would you like to redo the order?:  ");
            userChoice = promptUser(MenuPromptHandler.simpleResponse);

            //Cancel order if user selected "Yes"
            if (userChoice.equals("1")) processCancelOrderRequest();
        }
    }

    private PaymentOption promptForPayment() {
        System.out.println(MenuPromptHandler.paymentScreenHeader);
        promptInstructions("How would you like to pay?:  ");
        System.out.println(MenuPromptHandler.paymentOptions);
        return PaymentOption.valueFromChoice(promptMenuSelection("Payment Method: "));
    }

    private double promptChangeOwed(double orderTotal, PaymentOption payMethod) {
        //Retrieving money amount based on payment option
        double moneyForOrder = payMethod.getDollars();

        //To hold change from order transaction
        double changeDue = 0.0;

        if (moneyForOrder >= orderTotal) {
            changeDue += calculateChangeDue(moneyForOrder, orderTotal);
        } else {
            System.out.println("Pay amount is insufficient to cover cost of total order.");
        }

        return changeDue;
    }

    private double calculateChangeDue(double money, double orderTotal) {
        return money - orderTotal;
    }

    private boolean promptOrderConfirmation() {
        System.out.println(MenuPromptHandler.orderConfirmationScreenHeader);
        promptInstructions("Please confirm order details is correct:  ");
        userChoice = promptUser(MenuPromptHandler.simpleResponse);

        //Returns true if user selected "Yes"
        return userChoice.equals("1");
    }

    private void printIndividualOrderItems(List<OrderItem> items) {
        //Filtering for order items by category and printing them out
        List<OrderItem> sandwichesOnOrder = items.stream().filter(orderItem -> orderItem instanceof Sandwich).toList();
        System.out.println(sandwichesOnOrder);

        List<OrderItem> drinksOnOrder = items.stream().filter(orderItem -> orderItem instanceof Drink).toList();
        System.out.println(drinksOnOrder);

        List<OrderItem> chipsOnOrder = items.stream().filter(orderItem -> orderItem instanceof BagOfChips).toList();
        System.out.println(chipsOnOrder);
    }

    private List<OrderItem> retrieveAllOrderItems() {
        //Retrieving list of all order items
        return blankOrder.getOrderItems();
    }

    //Methods to retrieve user input and display prompts to user
    private String promptUser(String prompt) {
        System.out.print(ColorCodes.WHITE + prompt + ColorCodes.RESET);
        return userInput = inputSc.nextLine().trim();
    }

    private String promptMenuSelection(String prompt) {
        System.out.print(ColorCodes.WHITE + prompt + ColorCodes.RESET);
        return userInput = inputSc.nextLine().toUpperCase().trim();
    }

    private void promptInstructions(String prompt) {
        String[] textDetails = prompt.split(": ");
        System.out.println(ColorCodes.LIGHT_BLUE + textDetails[0] + ColorCodes.ORANGE_BOLD + ColorCodes.ITALIC + textDetails[1] + ColorCodes.RESET);
    }
}
