package com.pluralsight.deli.models;

import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.SandwichSize;

import java.util.List;

public class Sandwich extends OrderItem {
    private int size;
    private BreadType bread;
    private boolean isToasted;
    private List<Topping> toppings;

    public Sandwich(int size, BreadType bread, boolean isToasted) {
        this.size = size;
        this.bread = bread;
        this.isToasted = isToasted;
    }

    public int getSize() {
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

    public void addToppings(Topping t) {
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
