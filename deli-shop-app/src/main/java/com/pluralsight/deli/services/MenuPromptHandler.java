package com.pluralsight.deli.services;

public class MenuPromptHandler {
    public static String homeScreenMenuHeader = """
                =================================
                |        DELI SHOP (HOME)       |
                =================================
                """;
    public static String homePrompt = """
                Please make your selection:
                
                [1] New Order
                [X] Exit Application
                """;


   public static String orderScreenMenuHeader = """
                =================================
                |             ORDER             |
                =================================
                """;
   public static String orderPrompt = """
                Select from the following options:
                
                1) Add Sandwich
                2) Add Drink
                3) Add Chips
                4) Checkout
                0) Cancel Order
                """;


    public static String sandwichScreenMenuHeader = """
                =================================
                |        CREATE A SANDWICH      |
                =================================
                """;

    public static String breadTypeOptions = """
                A) White
                B) Wheat
                C) Rye
                D) Wrap
                """;

    public static String sandwichSizeOptions = """
                A) 4"
                B) 8"
                C) 12"
                """;

    public static String sandwichRegularToppings = """
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

    public static String sandwichPremiumMeatToppings = """
                A) Steak
                B) Ham
                C) Salami
                D) Roast Beef
                E) Chicken
                F) Bacon
                """;

   public static String sandwichPremiumCheeseToppings = """
                A) American
                B) Provolone
                C) Cheddar
                D) Swiss
                """;

    public static String simpleResponse = """
                    1) Yes
                    2) No
                    """;
}
