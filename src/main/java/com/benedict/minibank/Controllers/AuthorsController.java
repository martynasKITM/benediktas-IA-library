package com.benedict.minibank.Controllers;

import com.benedict.minibank.Models.Author;
import com.benedict.minibank.Models.Model;
import com.benedict.minibank.Utilities.AlertUtility;
import com.benedict.minibank.Utilities.DialogUtility;
import com.benedict.minibank.Views.MenuOptions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for managing authors in the application.
 * Handles actions such as adding, editing, and removing authors.
 */
public class AuthorsController implements Initializable {

    /** Button to trigger the creation of a new author. */
    @FXML
    public Button add_author;

    /** TableView for displaying the list of authors. */
    @FXML
    public TableView<Author> authors_table;

    /** TableColumn for displaying the ID of the author. */
    public TableColumn<Author, Integer> colID;

    /** Context menu item for removing an author. */
    public MenuItem removeMenuItem;

    /** TableColumn for displaying the first name of the author. */
    @FXML
    private TableColumn<Author, String> colFirstName;

    /** TableColumn for displaying the last name of the author. */
    @FXML
    private TableColumn<Author, String> colLastName;

    /** TableColumn for displaying the email of the author. */
    @FXML
    private TableColumn<Author, String> colEmail;

    /** TableColumn for displaying the city of the author. */
    @FXML
    private TableColumn<Author, String> colCity;

    /**
     * Initializes the controller.
     * Sets up table columns, loads author data, and assigns event handlers.
     *
     * @param url the location of the FXML file
     * @param resourceBundle resources used by the application
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableColumns();
        loadAuthorData();
        Model.getInstance().getAuthors();
        add_author.setOnAction(event -> onCreateAuthor());
        setRowFactoryForAuthorsTable();
        removeMenuItem.setOnAction(event -> onRemoveAuthor());
    }

    /**
     * Opens the view for creating a new author.
     */
    private void onCreateAuthor() {
        Model.getInstance().getViewFactory().getuserSelectedMenuItem().set(MenuOptions.CREATE_AUTHOR);
    }

    /**
     * Loads the list of authors from the model and sets it to the table view.
     * Refreshes the table to ensure it reflects the latest data.
     */
    private void loadAuthorData() {
        ObservableList<Author> authors = Model.getInstance().getAuthors();
        authors_table.setItems(authors);
    }

    /**
     * Initializes the table columns by binding them to the properties of the Author model.
     */
    private void initTableColumns() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    /**
     * Sets a row factory for the authors table to handle double-click events.
     * Double-clicking a row opens the edit dialog for the selected author.
     */
    private void setRowFactoryForAuthorsTable() {
        authors_table.setRowFactory(tv -> {
            TableRow<Author> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Author selectedAuthor = row.getItem();
                    editAuthor(selectedAuthor);
                }
            });
            return row;
        });
    }

    /**
     * Opens the dialog for editing an author's details.
     * Updates the table and model if the author is modified.
     *
     * @param author the author to be edited
     */
    private void editAuthor(Author author) {
        Optional<Author> result = DialogUtility.showEditAuthorDialog(author);
        result.ifPresent(updatedAuthor -> {
            authors_table.refresh();
            Model.getInstance().updateAuthor(updatedAuthor);
        });
    }

    /**
     * Handles the removal of the selected author from the table and database.
     * Displays a confirmation dialog before removing the author.
     */
    private void onRemoveAuthor() {
        Author selectedAuthor = authors_table.getSelectionModel().getSelectedItem();
        if (selectedAuthor == null) {
            AlertUtility.displayError("Please select an author. No row is selected.");
            return;
        }

        // Show confirmation dialog
        boolean confirmed = AlertUtility.displayConfirmation(
                "Are you sure you want to remove this author from the system?"
        );

        if (confirmed) {
            Model.getInstance().deleteAuthor(selectedAuthor.getId());
            authors_table.getItems().remove(selectedAuthor);
            authors_table.refresh();
            AlertUtility.displayInformation("Author removed successfully.");
        }
    }
}
