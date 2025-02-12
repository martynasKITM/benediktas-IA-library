package com.benedict.library.Controllers;

import com.benedict.library.Models.Author;
import com.benedict.library.Models.Model;
import com.benedict.library.Utilities.AlertUtility;
import com.benedict.library.Views.MenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the "Create Author" view.
 * This class handles the creation of new authors, including validating input fields,
 * triggering the author creation process in the Model, and updating the user interface.
 *
 * Implements the `Initializable` interface to perform setup logic after the FXML file is loaded.
 */
public class CreateAuthorController implements Initializable {

    // FXML elements for user input and feedback
    public TextField fName_fld; // Field for entering the author's first name
    public TextField lName_fld; // Field for entering the author's last name
    public TextField email_fld; // Field for entering the author's email address
    public TextField city_fld; // Field for entering the author's city
    public Button create_author_btn; // Button to trigger the author creation process
    public Label error_lbl; // Label to display error messages

    /**
     * Called to initialize the controller after the FXML file has been loaded.
     * Sets up event handling for the "Create Author" button.
     *
     * @param location  the location used to resolve relative paths for the root object, or null if not applicable.
     * @param resources the resources used to localize the root object, or null if not applicable.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create_author_btn.setOnAction(event -> onAuthor());
    }

    /**
     * Handles the logic for creating a new author.
     * Retrieves input from text fields, invokes the Model to create the author,
     * updates the user interface, and displays a success alert.
     */
    private void onAuthor() {
        String fName = fName_fld.getText(); // Get the first name from the input field
        String lName = lName_fld.getText(); // Get the last name from the input field
        String email = email_fld.getText(); // Get the email from the input field
        String city = city_fld.getText(); // Get the city from the input field

        // Create the author via the Model
        Model.getInstance().createAuthor(fName, lName, email, city);

        // Navigate to the "Authors" menu
        Model.getInstance().getViewFactory().getuserSelectedMenuItem().set(MenuOptions.AUTHORS);

        // Display a success message to the user
        AlertUtility.displayInformation("Autorius išsaugotas sėkmingai");

        // Clear input fields for the next entry
        emptyFields();



    }

    /**
     * Clears all input fields on the form.
     * Resets the text fields for the author's first name, last name, email, and city.
     */
    private void emptyFields() {
        fName_fld.setText(""); // Clear the first name field
        lName_fld.setText(""); // Clear the last name field
        email_fld.setText(""); // Clear the email field
        city_fld.setText(""); // Clear the city field
    }


}
