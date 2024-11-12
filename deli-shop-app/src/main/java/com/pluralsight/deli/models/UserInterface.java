package com.pluralsight.deli.models;

import com.pluralsight.deli.options.*;
import helpers.ColorCodes;
import com.pluralsight.deli.services.MenuPromptHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        DrinkType type = promptDrinkType();
        DrinkFlavor flavor = promptDrinkFlavor();

        //Instantiating a new drink
        Drink customerDrink = new Drink(size, type, flavor);

        //Adding customer drink item to order
        blankOrder.addToOrder(customerDrink);

        //Viewing customer's drink
        System.out.println(customerDrink.displayItem());
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
        System.out.println(chips.displayItem());
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
        return DrinkSize.valueOf(promptMenuSelection("Size: "));
    }

    private DrinkType promptDrinkType() {
        promptInstructions("What type of drink would you like?:  ");
        System.out.println(MenuPromptHandler.drinkTypeOptions);
        return DrinkType.valueOf(promptMenuSelection("Type: "));
    }

    private DrinkFlavor promptDrinkFlavor() {
        promptInstructions("What flavored drink would you like?:  ");
        System.out.println(MenuPromptHandler.drinkFlavorOptions);
        return DrinkFlavor.valueOf(promptMenuSelection("Flavor: "));
    }

    private ChipFlavor promptChipsFlavor() {
        promptInstructions("What chips would you like?:  ");
        System.out.println(MenuPromptHandler.chipsFlavorOptions);
        return ChipFlavor.valueOf(promptMenuSelection("Flavor: "));
    }

    private void promptSauces() {
        do {
            promptInstructions("Any sauces you like to add alongside your order?:  ");
            System.out.println(MenuPromptHandler.sauceOptions);
            SauceType sauce = SauceType.valueOf(promptMenuSelection("Sauce: "));

            blankOrder.addToOrder(sauce);

            promptInstructions("Would you like to include additional sauces?:  ");
            userChoice = promptUser(MenuPromptHandler.simpleResponse);
        } while (!userChoice.equalsIgnoreCase("2"));
    }

    private void promptFinalizeOrder() {
        //Retrieving list of all order items
        List<OrderItem> items = retrieveAllOrderItems();

        System.out.println(MenuPromptHandler.orderDetailsScreenHeader);

        //Checking all order items by category
        printIndividualOrderItems(items);

        //Calculating total price of every item on order and printing it out
        double orderTotal = blankOrder.getTotalCost(items);
        String printedTotal = String.format("Order total: $%.2f", orderTotal);
        System.out.println(printedTotal);

        //Prompting user for payment method
        PaymentOption payMethod = promptForPayment();
        int moneyForOrder = payMethod.getDollars();

        if (moneyForOrder >= orderTotal) {
            double changeDue = calculateChangeDue(moneyForOrder, orderTotal);
        } else {
            System.out.println("Pay amount is insufficient to cover cost of total order.");
        }
    }

    private PaymentOption promptForPayment() {
        System.out.println(MenuPromptHandler.paymentScreenHeader);

        promptInstructions("How would you like to pay?:  ");
        System.out.println(MenuPromptHandler.paymentOptions);

        return PaymentOption.valueOf(promptUser("Payment Method: "));
    }

    private double calculateChangeDue(int money, double orderTotal) {
        return money - orderTotal;
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
        List<OrderItem> items = blankOrder.getOrderItems();

        return items;
    }

    //Retrieves user input from a prompt
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
