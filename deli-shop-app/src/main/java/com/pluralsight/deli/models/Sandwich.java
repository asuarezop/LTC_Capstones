package com.pluralsight.deli.models;

import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.SandwichSize;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderItem {
    private SandwichSize size;
    private BreadType bread;
    private boolean isToasted;
    private boolean extraToppings;
    private List<Topping> toppings = new ArrayList<>();

    public Sandwich(SandwichSize size, BreadType bread) {
        this.size = size;
        this.bread = bread;
    }

    public SandwichSize getSize() {
        return size;
    }

    public BreadType getBread() {
        return bread;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public boolean isExtraToppings() {
        return extraToppings;
    }

    public void setExtraToppings(boolean extraToppings) {
        this.extraToppings = extraToppings;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addToppings(List<Topping> userToppings) {
        toppings.addAll(userToppings);
    }

    @Override
    public double getPrice() {
        double sandwichPrice = 0.0;
        double baseToppingPrice = 0.0;
        double extraMeatToppingsCharge = 0.0;
        double extraCheeseToppingsCharge = 0.0;

        //To calculate price of sandwich, have to take into account price of premium toppings
        for (Topping t: toppings) {
           if (t.getToppingType().equals("Premium") && isExtraToppings()) {
               if (size.equals(SandwichSize.FOUR_INCHES) && t.getToppingCategory().equals("Meat")) {
                   baseToppingPrice += 1.00;
                   extraMeatToppingsCharge += 0.50;
               } else if (size.equals(SandwichSize.EIGHT_INCHES) && t.getToppingCategory().equals("Meat")) {
                   baseToppingPrice += 2.00;
                   extraMeatToppingsCharge += 1.00;
               } else if (size.equals(SandwichSize.TWELVE_INCHES) && t.getToppingCategory().equals("Meat")) {
                   baseToppingPrice += 3.00;
                   extraMeatToppingsCharge += 1.50;
               } else if (size.equals(SandwichSize.FOUR_INCHES) && t.getToppingCategory().equals("Cheese")) {
                   baseToppingPrice += 0.75;
                   extraCheeseToppingsCharge += 0.30;
               } else if (size.equals(SandwichSize.EIGHT_INCHES) && t.getToppingCategory().equals("Cheese")) {
                   baseToppingPrice += 1.50;
                   extraCheeseToppingsCharge += 0.60;
               } else if (size.equals(SandwichSize.TWELVE_INCHES) && t.getToppingCategory().equals("Cheese")) {
                   baseToppingPrice += 2.25;
                   extraCheeseToppingsCharge += 0.90;
               }
           }
        }

        sandwichPrice += baseToppingPrice + extraMeatToppingsCharge + extraCheeseToppingsCharge;

        //Calculating price of all priceable items in a sandwich by size
        return switch (size) {
            case FOUR_INCHES -> sandwichPrice + 5.50;
            case EIGHT_INCHES -> sandwichPrice + 7.00;
            case TWELVE_INCHES -> sandwichPrice + 8.50;
        };
    }

    @Override
    public String displayItem() {
        //Where you would print individual sandwich to user
        return String.format("Sandwich specs: %s,%s,%s", size, bread, isToasted);
    }
}
