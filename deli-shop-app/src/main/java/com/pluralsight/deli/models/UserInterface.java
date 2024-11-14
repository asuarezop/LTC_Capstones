package com.pluralsight.deli.models;

import com.pluralsight.deli.services.UIProcessingHandler;
import com.pluralsight.deli.services.MenuPromptHandler;

import java.util.Scanner;

public class UserInterface {
    private UIProcessingHandler uiProcessor;

    //Initializing blankOrder with a new Order object instance
    private void init() {
        this.uiProcessor = new UIProcessingHandler();
        this.uiProcessor.blankOrder = new Order();
        this.uiProcessor.inputSc = new Scanner(System.in);
        this.uiProcessor.exitApp = false;
    }

    public void showHomeScreen() {
        do {
            init();
            System.out.println(MenuPromptHandler.homeScreenMenuHeader);
            System.out.println(MenuPromptHandler.homePrompt);
            uiProcessor.userInput = uiProcessor.inputSc.nextLine().trim().toUpperCase();

            switch (uiProcessor.userInput) {
                case "1":
                    uiProcessor.processNewOrderRequest();
                    break;
                case "X":
                    uiProcessor.exitApp = true;
                    break;
                default:
                    System.out.println("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!uiProcessor.exitApp);
    }
}
