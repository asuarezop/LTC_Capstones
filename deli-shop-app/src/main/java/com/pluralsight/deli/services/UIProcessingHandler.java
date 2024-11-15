package com.pluralsight.deli.services;

import com.pluralsight.deli.enums.*;
import com.pluralsight.deli.interfaces.OrderItem;
import com.pluralsight.deli.interfaces.Topping;
import com.pluralsight.deli.models.*;
import helpers.ColorCodes;

import java.util.List;
import java.util.Scanner;

public class UIProcessingHandler {
    public String userInput;

    public String userChoice;

    public Scanner inputSc;

    public boolean exitApp;

    public Order blankOrder;

    //Default constructor
    public UIProcessingHandler() {
    }

    public void processNewOrderRequest() {
        do {
            System.out.println(ColorCodes.CYAN + MenuPromptHandler.orderScreenMenuHeader + ColorCodes.RESET);
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
        //Prompt user if they'd like to order from signature sandwiches or create their own sandwich
        System.out.println(ColorCodes.ORANGE + MenuPromptHandler.signatureSandwichScreenMenuHeader + ColorCodes.RESET);

        UserInterface.promptInstructions("Would you like to order from our signature sandwiches?:  ");
        userChoice = UserInterface.promptUser(MenuPromptHandler.simpleResponse);

        if (userChoice.equals("1")) {
            UserInterface.promptCustomSandwich();
        } else {
            System.out.println(ColorCodes.ORANGE + MenuPromptHandler.sandwichScreenMenuHeader + ColorCodes.RESET);

            //Prompting to get sandwich size and type of bread
            BreadType sandwichBread = UserInterface.promptBreadType();
            System.out.println(ColorCodes.WHITE + sandwichBread + ColorCodes.RESET);
            SandwichSize size = UserInterface.promptSandwichSize();
            System.out.println(ColorCodes.WHITE + size + ColorCodes.RESET);
            SauceType spread =  UserInterface.promptSandwichSpread();
            System.out.println(ColorCodes.WHITE + spread + ColorCodes.RESET);

            //Instantiating a new sandwich
            Sandwich customerSandwich = new Sandwich(size, sandwichBread, spread);
            boolean finished;

            do {
                //Prompt to add regular toppings to sandwich
                UserInterface.promptAddToppings(customerSandwich);

                //Viewing regular toppings on sandwich
                List<Topping> addedToppings = customerSandwich.getToppings();
                System.out.println(ColorCodes.WHITE + addedToppings + ColorCodes.RESET);

                //Prompt to add premium toppings to sandwich
                UserInterface.promptAddPremToppings(customerSandwich);

                //Viewing premium toppings on sandwich
                List<Topping> appliedPremToppings = customerSandwich.getToppings();
                System.out.println(ColorCodes.WHITE + appliedPremToppings + ColorCodes.RESET);

                //Setting sandwich to toasted based on user choice
                boolean sandwichToasted = UserInterface.promptToasted();
                customerSandwich.setToasted(sandwichToasted);

                //Adding customer sandwich item to order
                blankOrder.addToOrder(customerSandwich);

                //Exit loop
                finished = true;
            } while (!finished);
        }
    }

    public void processAddDrinkRequest() {
        System.out.println(ColorCodes.YELLOW + MenuPromptHandler.drinkScreenMenuHeader + ColorCodes.RESET);

        //Getting size, type, and flavor of drink from user
        DrinkSize size = UserInterface.promptDrinkSize();
        DrinkType type = UserInterface.promptDrinkType();
        DrinkFlavor flavor = UserInterface.promptDrinkFlavor();

        //Instantiating a new drink
        Drink customerDrink = new Drink(size, type, flavor);

        //Adding customer drink item to order
        blankOrder.addToOrder(customerDrink);

        //Viewing customer's drink
        System.out.println(ColorCodes.WHITE + customerDrink + ColorCodes.RESET);
    }

    public void processAddChipsRequest() {
        System.out.println(ColorCodes.PURPLE + MenuPromptHandler.chipsScreenMenuHeader + ColorCodes.RESET);

        //Getting flavor of chips from user
        ChipFlavor flavor = UserInterface.promptChipsFlavor();

        //Instantiating a new bag of chips
        BagOfChips chips = new BagOfChips(flavor);

        //Adding chips item to order
        blankOrder.addToOrder(chips);

        //Viewing customer's chips
        System.out.println(ColorCodes.WHITE + chips + ColorCodes.RESET);
    }

    public void processCheckoutRequest() {
        System.out.println(ColorCodes.LIGHT_BLUE + MenuPromptHandler.checkoutScreenMenuHeader + ColorCodes.RESET);

        //Prompting user to add any additional sauces to their order
        UserInterface.promptSauces();

        //View order details and total pricing for every order item
        UserInterface.promptFinalizeOrder();
    }

    public void processCancelOrderRequest() {
        List<OrderItem> items = retrieveAllOrderItems();
        blankOrder.removeAllOrderItems(items);
    }

    public double calculateChangeDue(double money, double orderTotal) {
        return money - orderTotal;
    }

    public void printIndividualOrderItems(List<OrderItem> items) {
        //Filtering for order items by category and printing them out
        List<OrderItem> sandwichesOnOrder = items.stream().filter(orderItem -> orderItem instanceof Sandwich).toList();
        System.out.println(ColorCodes.WHITE + sandwichesOnOrder);

        List<OrderItem> drinksOnOrder = items.stream().filter(orderItem -> orderItem instanceof Drink).toList();
        System.out.println(drinksOnOrder);

        List<OrderItem> chipsOnOrder = items.stream().filter(orderItem -> orderItem instanceof BagOfChips).toList();
        System.out.println(chipsOnOrder);

        List<OrderItem> saucesOnOrder = items.stream().filter(orderItem -> orderItem instanceof SauceType).toList();
        System.out.println("Extra sauces: " + saucesOnOrder + ColorCodes.RESET);
    }

    public List<OrderItem> retrieveAllOrderItems() {
        //Retrieving list of all order items
        return blankOrder.getOrderItems();
    }
}
