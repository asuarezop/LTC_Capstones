package com.pluralsight.deli.models;

import com.pluralsight.deli.options.RegularTopping;

public interface Topping {
    void addTopping();

    void addTopping(RegularTopping t);
}
