package com.benedict.library.Utilities;

import com.benedict.library.Models.Author;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

/**
 * A utility class for displaying dialogs within the application.
 * This class provides reusable dialog functionality for editing Author objects.
 */
public class DialogUtility {

    /**
     * Displays a dialog for editing an Author's information.
     *
     * @param author the Author object to be edited. The dialog will prepopulate its fields
     *               with the existing data from the provided Author object.
     * @return an Optional containing the updated Author object if the user clicks the save button,
     *         or an empty Optional if the user cancels the dialog.
     */
    public static Optional<Author> showEditAuthorDialog(Author author) {
        // Create a dialog for editing the author's details
        Dialog<Author> dialog = new Dialog<>();
        dialog.setTitle("Redaguoti autorių");
        dialog.setHeaderText("Redaguokite pasirinkto autoriaus duomenis:");

        // Define the Save button type and add it to the dialog
        ButtonType saveButtonType = new ButtonType("Išsaugoti", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create and configure a grid layout for the dialog fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Input fields prepopulated with the current Author data
        TextField firstNameField = new TextField(author.getFirstName());
        TextField lastNameField = new TextField(author.getLastName());
        TextField emailField = new TextField(author.getEmail());
        TextField cityField = new TextField(author.getCity());

        // Add labels and input fields to the grid
        grid.add(new Label("Vardas:"), 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(new Label("Pavardė:"), 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(new Label("El. paštas:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Miestas:"), 0, 3);
        grid.add(cityField, 1, 3);

        // Set the grid as the content of the dialog
        dialog.getDialogPane().setContent(grid);

        // Define the result converter to handle dialog result
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                author.setFirstName(firstNameField.getText().trim());
                author.setLastName(lastNameField.getText().trim());
                author.setEmail(emailField.getText().trim());
                author.setCity(cityField.getText().trim());
                return author;
            }
            return null;
        });

        // Show the dialog and return the result
        return dialog.showAndWait();
    }
}
