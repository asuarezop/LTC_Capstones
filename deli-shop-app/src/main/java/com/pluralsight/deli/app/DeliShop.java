package com.pluralsight.deli.app;

import com.pluralsight.deli.models.UserInterface;
import java.io.IOException;

public class DeliShop {
    public static void main(String[] args) throws IOException {
        UserInterface ui = new UserInterface();

        //Call to Home Screen
        ui.showHomeScreen();
    }
}
