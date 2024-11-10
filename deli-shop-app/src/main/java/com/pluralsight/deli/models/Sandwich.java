package com.pluralsight.deli.models;

import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.RegularTopping;
import com.pluralsight.deli.options.SandwichSize;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends OrderItem {
    private SandwichSize size;
    private BreadType bread;
    private boolean isToasted;
    private static List<Topping> toppings = new ArrayList<>();

    public Sandwich() {}

    public Sandwich(SandwichSize size, BreadType bread, boolean isToasted) {
        this.size = size;
        this.bread = bread;
        this.isToasted = isToasted;
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

    public List<Topping> getToppings() {
        return toppings;
    }

    public static void addRegToppings(RegularTopping t) {
        toppings.add(t);
    }

    @Override
    public double getPrice() {
        return 0.0;
    }

    @Override
    public void displayItem() {
        //Where you would print individual sandwich to user
    }
}
