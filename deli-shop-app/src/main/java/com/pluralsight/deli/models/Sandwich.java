package com.pluralsight.deli.models;

import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.PremiumTopping;
import com.pluralsight.deli.options.RegularTopping;
import com.pluralsight.deli.options.SandwichSize;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends OrderItem {
    private SandwichSize size;
    private BreadType bread;
    private boolean isToasted;
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

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addToppings(List<Topping> userToppings) {
        toppings.addAll(userToppings);
    }

    public void addPremToppings(PremiumTopping t) {
        toppings.add(t);
    }

    @Override
    public double getPrice() {
        double sandwichPrice = 0.0;

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
