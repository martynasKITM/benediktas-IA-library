package com.benedict.library.Controllers;

import com.benedict.library.Models.Model;
import com.benedict.library.Utilities.AlertUtility;
import com.benedict.library.Utilities.UserAuthUtils;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the "Register" view.
 * This class handles user registration logic, including validating input fields,
 * checking if the username is already taken, and creating a new user in the system.
 * It also provides functionality for navigating to the login screen.
 *
 * Implements the `Initializable` interface to perform setup logic when the FXML file is loaded.
 */
public class RegisterController implements Initializable {

    // FXML elements for the user interface
    public TextField user_name_fld; // Field for the user to enter their username
    public Label password_lbl; // Label for the password field
    public PasswordField password_fld; // Field for the user to enter their password
    public PasswordField repeat_password_fld; // Field for the user to confirm their password
    public Button register_btn; // Button for submitting the registration form
    public Label password_lbl1; // Label for confirming the password
    public Label error_lbl; // Label for displaying error messages
    public Hyperlink login_link; // Link to navigate to the login screen

    /**
     * Called to initialize the controller after the FXML file has been loaded.
     * This method sets up the event handlers for the register button and the login link.
     *
     * @param location the location used to resolve relative paths for the root object, or null if not applicable.
     * @param resources the resources used to localize the root object, or null if not applicable.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set event handler for the register button
        register_btn.setOnAction(actionEvent -> onRegister());

        // Set event handler for the login hyperlink
        login_link.setOnAction(actionEvent -> onLogin());
    }

    /**
     * Handles the registration logic. This method performs validation checks on the input fields,
     * such as ensuring the username is not already registered and that the passwords match.
     * If the validation passes, a new user is created, and the user is navigated to the login screen.
     */
    public void onRegister() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();

        // Check if the username is already registered in the system
        if (Model.getInstance().isUserAlreadyRegistered(user_name_fld.getText())) {
            AlertUtility.displayError("Vartotojas, tokiu vardu sistemoje jau registruotas");
            emptyFields();
        }
        // Check if the passwords match
        else if (!UserAuthUtils.doPasswordsMatch(password_fld.getText(), repeat_password_fld.getText())) {
            AlertUtility.displayError("Nesutampa slaptažodžiai");
        }
        // If validation passes, create the new user and navigate to the login window
        else {
            Model.getInstance().createUser(user_name_fld.getText(), password_fld.getText());
            AlertUtility.displayInformation("Vartotojas sėkmingai užregistruotas, galite prisijungti");
            Model.getInstance().getViewFactory().showLoginWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        }
    }

    /**
     * Navigates the user to the login window.
     * This method closes the current registration window and opens the login window.
     */
    public void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    /**
     * Clears the input fields for username and password.
     * This method is used to reset the fields in case of an error during registration.
     */
    public void emptyFields() {
        user_name_fld.setText("");
        password_fld.setText("");
    }
}
