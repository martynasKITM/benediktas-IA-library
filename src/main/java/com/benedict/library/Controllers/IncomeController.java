package com.benedict.library.Controllers;

import com.benedict.library.Models.Model;
import com.benedict.library.Views.MenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the "Income" view.
 * This class handles the interactions and logic related to the "Income" table,
 * including the display of income data and the transition to the "Create Income" view.
 *
 * Implements the `Initializable` interface to perform setup logic when the FXML file is loaded.
 */
public class IncomeController implements Initializable {

    // FXML elements for the user interface
    public Button add_btn; // Button to add a new income
    public TableView income_table; // Table view to display income records
    public TableColumn date_col; // Column for the income date
    public TableColumn client_col; // Column for the client name
    public TableColumn service_col; // Column for the service provided
    public TableColumn total_col; // Column for the total income amount

    /**
     * Called to initialize the controller after the FXML file has been loaded.
     * Sets up event handling for the "Add Income" button.
     *
     * @param location  the location used to resolve relative paths for the root object, or null if not applicable.
     * @param resources the resources used to localize the root object, or null if not applicable.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        add_btn.setOnAction(event -> onCreateIncome());
    }

    /**
     * Handles the action for creating a new income record.
     * This method navigates the user to the "Create Income" view.
     */
    public void onCreateIncome() {
        // Navigate to the "Create Income" menu item
        Model.getInstance().getViewFactory().getuserSelectedMenuItem().set(MenuOptions.CREATE_INCOME);
    }
}
