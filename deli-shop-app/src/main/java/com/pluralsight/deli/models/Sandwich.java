package com.pluralsight.deli.models;

import com.pluralsight.deli.enums.BreadType;
import com.pluralsight.deli.enums.SandwichSize;
import com.pluralsight.deli.interfaces.OrderItem;
import com.pluralsight.deli.interfaces.Topping;

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
        double sandwichPrice;
        double toppingsCharge = 0.0;

        //To calculate price of sandwich, have to take into account price of premium toppings
        for (Topping topping : toppings) {
            if (topping.getToppingType().equals("Premium")) {

                //Calculating all topping charges
                toppingsCharge += calculateToppingCharges(size, topping, isExtraToppings());
            }
        }

        //Adding base sandwich price together with topping charges
        sandwichPrice = getBasePricing(size) + toppingsCharge;

        return sandwichPrice;
    }

    @Override
    public String toString() {
        return String.format("Sandwich size: %s, Bread type: %s, Toasted: %s, Toppings: %s", size, bread, isToasted, toppings);
    }

    @Override
    public String printToReceipt() {
        return String.format("Sandwich: %s %s %s $%.2f\n", size, bread, (isToasted) ? "GRILLED" : "CLASSIC", getPrice());
    }

    //Methods for calculating price of a sandwich
    private double getBasePricing(SandwichSize size) {
        double sandwichPrice = 0.0;

        //Calculating price of base sandwich by size
        return switch (size) {
            case FOUR_INCHES -> sandwichPrice + 5.50;
            case EIGHT_INCHES -> sandwichPrice + 7.00;
            case TWELVE_INCHES -> sandwichPrice + 8.50;
        };
    }

    private double calculateToppingCharges(SandwichSize size, Topping topping, boolean hasExtraToppings) {
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

        //If the user ordered extra toppings, return base sandwich price with extra toppings charge. Otherwise, return base pricing
        return (hasExtraToppings) ? baseToppingPrice + extraToppingCharge : baseToppingPrice;
    }
}
