package com.pluralsight.deli.enums;

import com.pluralsight.deli.interfaces.Topping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SandwichType {
    BLT("A", SandwichSize.EIGHT_INCHES, BreadType.WHITE, SauceType.RANCH, true, List.of(RegularTopping.LETTUCE, RegularTopping.TOMATOES, PremiumTopping.BACON, PremiumTopping.CHEDDAR)),
    PHILLY_CHEESE_STEAK("B", SandwichSize.EIGHT_INCHES, BreadType.WHITE, SauceType.MAYO, true, List.of(RegularTopping.PEPPERS, PremiumTopping.STEAK, PremiumTopping.AMERICAN)),
    ITALIAN("C", SandwichSize.EIGHT_INCHES, BreadType.CIABATTA, SauceType.ITALIAN_DRESSING, false, List.of(RegularTopping.LETTUCE, RegularTopping.TOMATOES, RegularTopping.ONIONS, RegularTopping.PEPPERS, RegularTopping.OLIVES, PremiumTopping.SALAMI, PremiumTopping.PEPPERONI, PremiumTopping.PROVOLONE)),
    MEATBALL("D", SandwichSize.EIGHT_INCHES, BreadType.BAGUETTE, SauceType.MARINA, true, List.of(PremiumTopping.MEATBALL, PremiumTopping.MOZZARELLA, PremiumTopping.PARMESAN)),
    ULTIMATE("E", SandwichSize.TWELVE_INCHES, BreadType.MULTIGRAIN, SauceType.HONEY_MUSTARD, true, List.of(RegularTopping.LETTUCE, RegularTopping.TOMATOES, RegularTopping.ONIONS, RegularTopping.PICKLES, PremiumTopping.TURKEY, PremiumTopping.HAM, PremiumTopping.ROAST_BEEF, PremiumTopping.BACON, PremiumTopping.SWISS, PremiumTopping.PROVOLONE)),
    ALL_AMERICAN("F", SandwichSize.EIGHT_INCHES, BreadType.CIABATTA, SauceType.MAYO, true, List.of(RegularTopping.LETTUCE, RegularTopping.TOMATOES, RegularTopping.ONIONS, PremiumTopping.TURKEY, PremiumTopping.HAM, PremiumTopping.BACON, PremiumTopping.AMERICAN));

    private static final Map<String, SandwichType> BY_CHOICE = new HashMap<>();

    static {
        for (SandwichType st : values()) {
            BY_CHOICE.put(st.choice, st);
        }
    }

    private final String choice;
    private SandwichSize size;
    private BreadType bread;
    private SauceType sauce;
    private boolean isToasted;
    private List<Topping> toppings;

    SandwichType(String userChoice, SandwichSize size, BreadType bread, SauceType sauce, boolean isToasted, List<Topping> toppings) {
        this.choice = userChoice;
        this.size = size;
        this.bread = bread;
        this.sauce = sauce;
        this.isToasted = isToasted;
        this.toppings = toppings;
    }

    public SandwichSize getSize() {
        return size;
    }

    public BreadType getBread() {
        return bread;
    }

    public SauceType getSauce() {
        return sauce;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public static SandwichType valueFromChoice(String value) {
        return BY_CHOICE.get(value);
    }
}
