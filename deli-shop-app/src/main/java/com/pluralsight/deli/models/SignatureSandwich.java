package com.pluralsight.deli.models;

import com.pluralsight.deli.enums.BreadType;
import com.pluralsight.deli.enums.SandwichSize;
import com.pluralsight.deli.enums.SandwichType;
import com.pluralsight.deli.enums.SauceType;
import com.pluralsight.deli.interfaces.Topping;

import java.util.ArrayList;
import java.util.List;

public class SignatureSandwich extends Sandwich {
    private SandwichType type;
    private List<Topping> toppings = new ArrayList<>();

    public SignatureSandwich(SandwichSize size, BreadType bread, SauceType spread) {
        super(size, bread, spread);
    }

    public SandwichType getType() {
        return type;
    }

    public void setType(SandwichType type) {
        this.type = type;
    }

    @Override
    public SandwichSize getSize() {
        return super.getSize();
    }

    @Override
    public BreadType getBread() {
        return super.getBread();
    }

    @Override
    public SauceType getSpread() {
        return super.getSpread();
    }

    @Override
    public boolean isToasted() {
        return super.isToasted();
    }

    @Override
    public void setToasted(boolean toasted) {
        super.setToasted(toasted);
    }

    @Override
    public boolean isExtraToppings() {
        return super.isExtraToppings();
    }

    @Override
    public void setExtraToppings(boolean extraToppings) {
        super.setExtraToppings(extraToppings);
    }

    @Override
    public List<Topping> getToppings() {
        return super.getToppings();
    }

    @Override
    public void addToppings(List<Topping> userToppings) {
        super.addToppings(userToppings);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String printToReceipt() {
        return super.printToReceipt();
    }
}
