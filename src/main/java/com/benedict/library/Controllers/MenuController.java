package com.benedict.library.Controllers;

import com.benedict.library.Models.Model;
import com.benedict.library.Views.MenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the "Menu" view.
 * This class handles the user interface interactions for the main menu, such as navigating between different sections of the application,
 * logging out, and displaying the current logged-in user's information.
 *
 * Implements the `Initializable` interface to perform setup logic when the FXML file is loaded.
 */
public class MenuController implements Initializable {

    // FXML elements for the user interface
    public Button logout_btn; // Button for logging out
    public Button authors_btn; // Button for navigating to the authors section
    public Text user_name; // Text to display the logged-in user's name
    public Button income_btn; // Button for navigating to the income section

    /**
     * Called to initialize the controller after the FXML file has been loaded.
     * This method sets up the logged-in user's name and adds action listeners to the menu buttons.
     *
     * @param url the location used to resolve relative paths for the root object, or null if not applicable.
     * @param resourceBundle the resources used to localize the root object, or null if not applicable.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Display the logged-in user's name in the menu
        System.out.println("Userio info: " + Model.getInstance().getLoggedUserName());
        user_name.setText(Model.getInstance().getLoggedUserName());

        // Add listeners to the menu buttons
        addListeners();
    }

    /**
     * Sets up event listeners for the menu buttons.
     * This method defines the actions for navigating to the authors section, income section, and logging out.
     */
    private void addListeners() {
        // Listener for the "Authors" button to navigate to the authors section
        authors_btn.setOnAction(event -> onAuthor());

        // Listener for the "Logout" button to log out the user
        logout_btn.setOnAction(event -> onLogout());

        // Listener for the "Income" button to navigate to the income section
        income_btn.setOnAction(event -> onIncome());
    }

    /**
     * Navigates the user to the authors section.
     * This method sets the current menu option to "AUTHORS".
     */
    private void onAuthor() {
        // Navigate to the authors section
        Model.getInstance().getViewFactory().getuserSelectedMenuItem().set(MenuOptions.AUTHORS);
    }

    /**
     * Navigates the user to the income section.
     * This method sets the current menu option to "INCOME".
     */
    private void onIncome() {
        // Navigate to the income section
        Model.getInstance().getViewFactory().getuserSelectedMenuItem().set(MenuOptions.INCOME);
    }

    /**
     * Handles the logout action.
     * This method closes the current menu window, opens the login window, and resets the login flag.
     */
    private void onLogout() {
        // Get the current stage (menu window)
        Stage stage = (Stage) authors_btn.getScene().getWindow();

        // Close the current stage (menu window)
        Model.getInstance().getViewFactory().closeStage(stage);

        // Show the login window
        Model.getInstance().getViewFactory().showLoginWindow();

        // Set the login success flag to false
        Model.getInstance().setClientAdminSuccessFlag(false);
    }
}
