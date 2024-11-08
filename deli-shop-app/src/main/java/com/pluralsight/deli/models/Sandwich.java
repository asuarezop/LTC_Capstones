package com.pluralsight.deli.models;

import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.PremiumTopping;
import com.pluralsight.deli.options.RegularTopping;
import com.pluralsight.deli.options.SandwichSize;

import java.util.List;

public class Sandwich extends OrderItem implements Priceable {
    private SandwichSize size;
    private BreadType bread;
    private RegularTopping veggies;
    private PremiumTopping meat;
    private PremiumTopping cheese;
    private boolean isToasted;
    private List<RegularTopping> toppings;

    public Sandwich(SandwichSize size, BreadType bread, RegularTopping veggies, PremiumTopping meat, PremiumTopping cheese, boolean isToasted) {
        this.size = size;
        this.bread = bread;
        this.veggies = veggies;
        this.meat = meat;
        this.cheese = cheese;
        this.isToasted = isToasted;
    }

    public SandwichSize getSize() {
        return size;
    }

    public BreadType getBread() {
        return bread;
    }

    public RegularTopping getVeggies() {
        return veggies;
    }

    public PremiumTopping getMeat() {
        return meat;
    }

    public PremiumTopping getCheese() {
        return cheese;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public List<RegularTopping> getToppings() {
        return toppings;
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
