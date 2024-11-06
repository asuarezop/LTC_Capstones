package com.pluralsight.deli.models;

import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.SandwichSize;

import java.util.List;

public class Sandwich implements Priceable {
    private BreadType bread;
    private SandwichSize size;
    private boolean isToasted;
    private List<Topping> toppings;

    public Sandwich(BreadType bread, SandwichSize size, boolean isToasted) {
        this.bread = bread;
        this.size = size;
        this.isToasted = isToasted;
    }

    public BreadType getBread() {
        return bread;
    }

    public SandwichSize getSize() {
        return size;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping t) {
        toppings.add(t);
    }

    @Override
    public double getPrice() {
        return 0.0;
    }


}
