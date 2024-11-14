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
                G) American
                H) Provolone
                I) Cheddar
                J) Swiss
                """;

    public static String simpleResponse = """
                    1) Yes
                    2) No
                    """;

    public static String drinkScreenMenuHeader = """
                =================================
                |             DRINK             |
                =================================
                """;

    public static String drinkSizeOptions = """
                A) Small
                B) Medium
                C) Large
                """;

    public static String drinkTypeOptions = """
            A) Water
            B) Soft Drink
            C) Iced Tea
            """;

    public static String drinkFlavorOptions = """
            A) Cola
            B) Lemon-Lime
            C) Orange
            D) Dr Pepper
            E) Fruit Punch
            F) Berry
            G) Root Beer
            H) Lemonade
            I) Lemon Water
            J) Seltzer Water
            K) Cucumber Water
            L) Sweet Tea
            M) Lemon Tea
            N) Green Tea
            """;

    public static String chipsScreenMenuHeader = """
                =================================
                |             CHIPS             |
                =================================
                """;

    public static String chipsFlavorOptions = """
            A) Potato
            B) BBQ
            C) Oven Baked
            D) Sun Dried Tomato
            E) Salt N Pepper
            F) Multigrain
            G) Cheddar
            H) Jalapeno
            """;


    public static String checkoutScreenMenuHeader = """
                =================================
                |            CHECKOUT           |
                =================================
                """;


    public static String sauceOptions = """
            A) Mayo
            B) Mustard
            C) Ketchup
            D) Ranch
            E) Thousand Islands
            F) Vinaigrette
            G) Au Jus
            """;

    public static String orderDetailsScreenHeader = """
            =================================
            |          ORDER_DETAILS        |
            =================================
            """;

    public static String orderConfirmationScreenHeader = """
            =================================
            |          CONFIRM ORDER        |
            =================================
            """;

    public static String cancelScreenHeader = """
            =================================
            |             CANCEL            |
            =================================
            """;

    public static String paymentScreenHeader = """
            =================================
            |             PAYMENT           |
            =================================
            """;

    public static String paymentOptions = """
                    1) Cash
                    2) Debit
                    3) Credit
                    """;
}
