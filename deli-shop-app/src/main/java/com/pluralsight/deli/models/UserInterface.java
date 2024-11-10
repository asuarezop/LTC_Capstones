package com.pluralsight.deli.models;

import JavaHelpers.ColorCodes;
import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.PremiumTopping;
import com.pluralsight.deli.options.RegularTopping;
import com.pluralsight.deli.options.SandwichSize;

import java.io.IOException;
import java.util.ArrayList;
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
            init();
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

        String breadTypeOptions = """
                A) White
                B) Wheat
                C) Rye
                D) Wrap
                """;

        String sandwichSizeOptions = """
                A) 4"
                B) 8"
                C) 12"
                """;

        String sandwichRegularToppings = """
                A) Lettuce
                B) Peppers
                C) Onions
                D) Tomatoes
                E) Jalapenos
                F) Cucumbers
                G) Pickles
                H) Guacamole
                I) Mushrooms
                """;

        String sandwichPremiumMeatToppings = """
                A) Steak
                B) Ham
                C) Salami
                D) Roast Beef
                E) Chicken
                F) Bacon
                """;

        String sandwichPremiumCheeseToppings = """
                A) American
                B) Provolone
                C) Cheddar
                D) Swiss
                """;

        String simpleResponse = """
                    1) Yes
                    2) No
                    """;

        System.out.println(sandwichScreenMenuHeader);
        promptInstructions("Enter type of bread:  ");
        System.out.println(breadTypeOptions);
        //TODO - display list of values for bread using lambda expression
        BreadType sandwichBread = BreadType.valueOf(promptMenuSelection("Bread: "));

        promptInstructions("Enter sandwich size:  ");
        System.out.println(sandwichSizeOptions);
        SandwichSize size = SandwichSize.valueOfSize(promptMenuSelection("Size: "));
        System.out.println(size);

        boolean finished = false;
        do {
            //Initializing a new sandwich object
            Sandwich customerSandwich = new Sandwich();

            promptInstructions("Enter sandwich toppings:  ");
            System.out.println(sandwichRegularToppings);
            RegularTopping sandwichVeggies = RegularTopping.valueOf(promptMenuSelection("Toppings: "));
            System.out.println(sandwichVeggies); //test to check value (working)

            //Viewing user's added toppings of their sandwich
            Sandwich.addRegToppings(sandwichVeggies);
            List<Topping> appliedToppings = customerSandwich.getToppings();
            System.out.println(appliedToppings);

            promptInstructions("Would you like to add more toppings?:  ");
            response = promptUser(simpleResponse);

            if (response.equalsIgnoreCase("2")) {

                promptInstructions("Enter any premium toppings to add onto sandwich:  ");
                System.out.println(sandwichPremiumMeatToppings);
                PremiumTopping meats = PremiumTopping.valueOf(promptMenuSelection("Meat: "));
                System.out.println(sandwichPremiumCheeseToppings);
                PremiumTopping cheeses = PremiumTopping.valueOf(promptMenuSelection("Cheese: "));
                Sandwich.addPremToppings(meats);
                Sandwich.addPremToppings(cheeses);

                List<Topping> appliedPremToppings = customerSandwich.getToppings();
                System.out.println(appliedPremToppings);

                promptInstructions("Would you like to add more premium toppings?:  ");
                String addAdditionalPremToppings = promptUser(simpleResponse);

                if (addAdditionalPremToppings.equalsIgnoreCase("2")) {
                    promptInstructions("Would you like your sandwich toasted?:  ");
                    response = promptUser(simpleResponse);

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



//        if (wantsExtra.equalsIgnoreCase("Yes")) {
//            List<Topping> otherToppings = new ArrayList<>();
//            promptUser("Other Toppings: ");
//            System.out.println(sandwichPremiumMeatToppings);
//            PremiumTopping meat = PremiumTopping.valueOf(promptMenuSelection("Meat: "));
//            System.out.println(sandwichPremiumCheeseToppings);
//            PremiumTopping cheese = PremiumTopping.valueOf(promptMenuSelection("Cheese: "));
//        }
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
