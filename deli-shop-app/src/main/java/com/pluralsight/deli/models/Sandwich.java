package com.pluralsight.deli.models;

import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.SandwichSize;

import java.util.List;

public class Sandwich implements Priceable {
    private BreadType bread;
    private SandwichSize size;
    private List<Topping> toppings;
    private boolean isToasted;


    public void addTopping(Topping t) {
        toppings.add(t);
    }

    @Override
    public double getPrice() {
        return 0.0;
    }


}
