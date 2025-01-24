package com.benedict.library.Controllers;

import com.benedict.library.Models.Model;
import com.benedict.library.Utilities.AlertUtility;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the "Login" view.
 * This class handles user authentication, including login and registration actions.
 * It validates the login credentials and either shows an error message or navigates the user to the admin window.
 *
 * Implements the `Initializable` interface to set up actions when the FXML file is loaded.
 */
public class LoginController implements Initializable {

    // FXML elements for user input and interaction
    public Label password_lbl; // Label for password field
    public TextField payee_address_fld; // Field for entering the username/email (payee address)
    public PasswordField password_fld; // Field for entering the password
    public Button login_btn; // Button to trigger the login process
    public Label error_lbl; // Label to display error messages (unused in this implementation)
    public Hyperlink register_link; // Link to navigate to the registration page

    /**
     * Called to initialize the controller after the FXML file has been loaded.
     * This method sets up event handlers for the login button and the register link.
     *
     * @param url the location used to resolve relative paths for the root object, or null if not applicable.
     * @param resourceBundle the resources used to localize the root object, or null if not applicable.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the action handler for the login button
        login_btn.setOnAction(actionEvent -> onLogin());

        // Set up the action handler for the register link
        register_link.setOnAction(actionEvent -> onRegister());
    }

    /**
     * Handles the login action.
     * This method validates the credentials entered by the user and either logs them in or displays an error.
     *
     * If login is successful, it opens the admin window and closes the login stage.
     * If login fails, it clears the input fields and displays an error message.
     */
    public void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow(); // Get the current stage (login window)

        // Check the user's credentials
        Model.getInstance().checkCredentials(payee_address_fld.getText(), password_fld.getText());

        // If login is successful, open the admin window and close the login window
        if (Model.getInstance().getAdminSuccessFlag()) {
            Model.getInstance().getViewFactory().showAdminWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            // If login fails, clear the input fields and show an error message
            payee_address_fld.setText("");
            password_fld.setText("");
            AlertUtility.displayError("Neteisingi prisijungimo duomenys."); // Display error alert
        }
    }

    /**
     * Handles the register action.
     * This method navigates the user to the registration window.
     */
    public void onRegister() {
        Stage stage = (Stage) error_lbl.getScene().getWindow(); // Get the current stage (login window)

        // Navigate to the registration window
        Model.getInstance().getViewFactory().showRegisterWindow();

        // Close the current login stage
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
