package com.pluralsight.deli.models;

import com.pluralsight.deli.options.BreadType;
import com.pluralsight.deli.options.PremiumTopping;
import com.pluralsight.deli.options.RegularTopping;
import com.pluralsight.deli.options.SandwichSize;

import java.util.List;

public class Sandwich implements Priceable {
    private BreadType bread;
    private SandwichSize size;
    private boolean isToasted;
    private RegularTopping regTopping;
    private PremiumTopping premTopping;
    private List<Topping> toppings;

    public Sandwich(BreadType bread, SandwichSize size, boolean isToasted, RegularTopping regTopping, PremiumTopping premTopping) {
        this.bread = bread;
        this.size = size;
        this.isToasted = isToasted;
        this.regTopping = regTopping;
        this.premTopping = premTopping;
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

    public RegularTopping getRegTopping() {
        return regTopping;
    }

    public PremiumTopping getPremTopping() {
        return premTopping;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(RegularTopping t) {
        toppings.add(t);
    }

    @Override
    public double getPrice() {
        return 0.0;
    }


}
