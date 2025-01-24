package com.benedict.library;

import com.benedict.library.Models.Model;
import com.benedict.library.Utilities.AlertUtility;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * The main entry point of the MiniBank application.
 * This class extends {@link Application} and handles the initialization
 * of the application's main window, either showing the login or register screen depending
 * on whether there are registered users.
 */
public class App extends Application {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    /**
     * Starts the application by checking if there are any registered users.
     * If users exist, the login window is displayed.
     * If no users are found, the user is prompted to register first.
     *
     * @param stage the primary stage for this JavaFX application, which will be used to show the main window
     */
    @Override
    public void start(Stage stage){
        // Check if there are registered users
        if (Model.getInstance().hasRegisteredUsers()){
            // If users exist, show the login window
            Model.getInstance().getViewFactory().showLoginWindow();
        } else {
            // If no users exist, display an information alert and show the register window
            AlertUtility.displayInformation("Prieš pradedant darbą su sistema turite užregistruoti bent vieną vartotoją");
            Model.getInstance().getViewFactory().showRegisterWindow();
        }
    }
}
