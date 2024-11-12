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
        double toppingsCharge = 0.0;

        //To calculate price of sandwich, have to take into account price of premium toppings
        for (Topping topping : toppings) {
            if (topping.getToppingType().equals("Premium") && isExtraToppings()) {

                toppingsCharge += getToppingPricing(size, topping);
            }
        }

        sandwichPrice = getBasePricing(size) + toppingsCharge;

      return sandwichPrice;
    }

    @Override
    public String toString() {
        return String.format("Sandwich size: %s, Bread type: %s, Toasted: %s, Toppings: %s", size, bread, isToasted, toppings);
    }

    @Override
    public String displayItem() {
        //Where you would print individual sandwich to user
        return String.format("Sandwich specs: %s,%s,%s", size, bread, isToasted);
    }

    //Methods for calculating price of a sandwich
    private double getBasePricing(SandwichSize size) {
        double sandwichPrice = 0.0;

        //Calculating price of all priceable items in a sandwich by size
        return switch (size) {
            case FOUR_INCHES -> sandwichPrice + 5.50;
            case EIGHT_INCHES -> sandwichPrice + 7.00;
            case TWELVE_INCHES -> sandwichPrice + 8.50;
        };
    }

    private double getToppingPricing(SandwichSize size, Topping topping) {
        double baseToppingPrice = 0.0;
        double extraToppingCharge = 0.0;

        switch (size) {
            case FOUR_INCHES -> {
                if (topping.getToppingCategory().equalsIgnoreCase("Meat")) {
                    baseToppingPrice += 1.00;
                    extraToppingCharge += 0.50;
                } else if (topping.getToppingCategory().equalsIgnoreCase("Cheese")) {
                    baseToppingPrice += 0.75;
                    extraToppingCharge += 0.30;
                }
            }
            case EIGHT_INCHES -> {
                if (topping.getToppingCategory().equalsIgnoreCase("Meat")) {
                    baseToppingPrice += 2.00;
                    extraToppingCharge += 1.00;
                } else if (topping.getToppingCategory().equalsIgnoreCase("Cheese")) {
                    baseToppingPrice += 1.50;
                    extraToppingCharge += 0.60;
                }
            }
            case TWELVE_INCHES -> {
                if (topping.getToppingCategory().equalsIgnoreCase("Meat")) {
                    baseToppingPrice += 3.00;
                    extraToppingCharge += 1.50;
                } else if (topping.getToppingCategory().equalsIgnoreCase("Cheese")) {
                    baseToppingPrice += 2.25;
                    extraToppingCharge += 0.90;
                }
            }
            default -> {
                baseToppingPrice = 0.0;
                extraToppingCharge = 0.0;
            }
        }

        return baseToppingPrice + extraToppingCharge;
    }
}
