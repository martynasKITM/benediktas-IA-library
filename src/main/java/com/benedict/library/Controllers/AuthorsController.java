package com.benedict.library.Controllers;

import com.benedict.library.Models.Author;
import com.benedict.library.Models.Model;
import com.benedict.library.Utilities.AlertUtility;
import com.benedict.library.Utilities.DialogUtility;
import com.benedict.library.Views.MenuOptions;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    public TableColumn<Author, Integer> colID;

    /** Context menu item for removing an author. */
    @FXML
    public MenuItem removeMenuItem;

    public TextField filterFirstName;
    public TextField filterLastName;
    public TextField filterCity;
    public Button filterButton;

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

    private FilteredList<Author> filteredAuthors;

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

        // Initialize filteredAuthors here
        filteredAuthors = new FilteredList<>(Model.getInstance().getAuthors());

        // Bind the table to filteredAuthors
        authors_table.setItems(filteredAuthors);

        add_author.setOnAction(event -> onCreateAuthor());
        setRowFactoryForAuthorsTable();
        removeMenuItem.setOnAction(event -> onRemoveAuthor());
        filterButton.setOnAction(event -> applyFilters());
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

        // Add a listener to refresh table automatically when data changes
        authors.addListener((ListChangeListener<Author>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                    authors_table.refresh();
                }
            }
        });

        authors_table.refresh();
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
            Model.getInstance().updateAuthor(updatedAuthor);
            loadAuthorData(); // Reload data to reflect changes
        });
    }

    /**
     * Handles the removal of the selected author from the table and database.
     * Displays a confirmation dialog before removing the author.
     */
    private void onRemoveAuthor() {
        Author selectedAuthor = authors_table.getSelectionModel().getSelectedItem();
        if (selectedAuthor == null) {
            AlertUtility.displayError("Pasirinkite autorių.");
            return;
        }

        boolean confirmed = AlertUtility.displayConfirmation(
                "Ar tikrai norite pašalinti autorių iš sistemos?"
        );

        if (confirmed) {
            Model.getInstance().deleteAuthor(selectedAuthor.getId());
            ObservableList<Author> authors = authors_table.getItems();
            authors.remove(selectedAuthor);
            authors_table.refresh();
            AlertUtility.displayInformation("Autorius pašalintas sėkmingai.");
        }
    }

    /**
     * Applies the filters to the list of authors based on the provided input.
     * Filters authors by first name, last name, and city.
     */
    private void applyFilters() {
        String firstNameFilter = filterFirstName.getText().toLowerCase();
        String lastNameFilter = filterLastName.getText().toLowerCase();
        String cityFilter = filterCity.getText().toLowerCase();

        filteredAuthors.setPredicate(author -> {
            if (!firstNameFilter.isEmpty() && !author.getFirstName().toLowerCase().contains(firstNameFilter)) {
                return false;
            }
            if (!lastNameFilter.isEmpty() && !author.getLastName().toLowerCase().contains(lastNameFilter)) {
                return false;
            }
            if (!cityFilter.isEmpty() && !author.getCity().toLowerCase().contains(cityFilter)) {
                return false;
            }
            return true;
        });
    }
}
