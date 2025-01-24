package com.benedict.library.Controllers;

import com.benedict.library.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class responsible for routing between different views within the admin interface.
 * The class listens for changes in the selected menu option and dynamically updates the content
 * in the center of the `BorderPane` based on the selected view.
 *
 * Implements the `Initializable` interface to perform initialization tasks when the FXML file is loaded.
 */
public class RouteController implements Initializable {

    // FXML element representing the main container for the admin interface
    public BorderPane admin_parent; // Main layout container for admin views

    /**
     * Called to initialize the controller after the FXML file has been loaded.
     * This method listens for changes in the selected menu item and updates the view displayed
     * in the center of the BorderPane accordingly.
     *
     * @param url the location used to resolve relative paths for the root object, or null if not applicable.
     * @param resourceBundle the resources used to localize the root object, or null if not applicable.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Listener for changes in the selected menu item
        Model.getInstance().getViewFactory().getuserSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            // Based on the selected menu item, update the center view of the BorderPane
            switch (newVal) {
                case AUTHORS:
                    // Display the authors view
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getAuthorsView());
                    break;
                case CREATE_AUTHOR:
                    // Display the create author view
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateAuthorView());
                    break;
                case INCOME:
                    // Display the income view
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getIncomeView());
                    break;
                case CREATE_INCOME:
                    // Display the create income view
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateIncomeView());
                    break;
                default:
                    // Default case (fallback to the authors view if no match)
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getAuthorsView());
                    break;
            }
        });
    }
}
