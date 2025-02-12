package com.benedict.library.Models;

import com.benedict.library.Views.ViewFactory;
import com.benedict.library.dao.AuthorDAO;
import com.benedict.library.dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * The central model class for the Library application.
 * This class is a singleton that serves as a bridge between the application's business logic,
 * data access objects (DAOs), and views. It manages the application's state and user data.
 */
public class Model {

    private static Model model; // Singleton instance of the Model class
    private final ViewFactory viewFactory; // Factory for managing application views
    public final UserDAO userDAO; // DAO for user-related database operations
    public final AuthorDAO authorDAO; // DAO for author-related database operations
    private boolean loginSuccessFlag; // Flag to track login success
    private final ObservableList<Author> authors; // Observable list of authors for UI updates
    private User currentUser; // The currently logged-in user

    /**
     * Private constructor to enforce the singleton pattern.
     * Initializes DAOs, the view factory, and application state variables.
     */
    private Model() {
        this.viewFactory = new ViewFactory();
        this.userDAO = new UserDAO(new DatabaseDriver().getConnection());
        this.authorDAO = new AuthorDAO(new DatabaseDriver().getConnection());
        this.loginSuccessFlag = false;
        this.currentUser = null;
        this.authors = FXCollections.observableArrayList();
        loadAuthorsFromDB();
    }

    /**
     * Returns the singleton instance of the Model class.
     *
     * @return the single instance of the Model.
     */
    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    /**
     * Gets the ViewFactory instance for managing the application's views.
     *
     * @return the ViewFactory instance.
     */
    public ViewFactory getViewFactory() {
        return viewFactory;
    }


    /** load all authors from DB **/

    private void loadAuthorsFromDB() {
        authors.setAll(authorDAO.findAll()); // Užtikrina, kad ObservableList bus atnaujintas
    }

    /**
     * Gets the login success flag for admin login attempts.
     *
     * @return true if the admin login is successful, false otherwise.
     */
    public boolean getAdminSuccessFlag() {
        return this.loginSuccessFlag;
    }

    /**
     * Sets the login success flag for admin login attempts.
     *
     * @param flag the new state of the login success flag.
     */
    public void setClientAdminSuccessFlag(boolean flag) {
        this.loginSuccessFlag = flag;
    }

    /**
     * Checks if there are any registered users in the database.
     *
     * @return true if at least one user is registered, false otherwise.
     */
    public boolean hasRegisteredUsers() {
        return userDAO.countUsers() > 0;
    }

    /**
     * Checks if a user with the given username is already registered.
     *
     * @param userName the username to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean isUserAlreadyRegistered(String userName) {
        return userDAO.isUserExist(userName);
    }

    /**
     * Creates a new user in the database.
     *
     * @param userName the username of the new user.
     * @param password the password of the new user.
     */
    public void createUser(String userName, String password) {
        userDAO.createUser(userName, password, LocalDate.now());
    }

    /**
     * Checks user credentials and sets the current user if they are valid.
     *
     * @param userName the username to check.
     * @param password the password to check.
     */
    public void checkCredentials(String userName, String password) {
        User user = userDAO.findUserByCredentials(userName, password);
        if (user != null) {
            this.loginSuccessFlag = true;
            this.currentUser = user;
        }
    }

    /**
     * Gets the username of the currently logged-in user.
     *
     * @return the username of the logged-in user, or null if no user is logged in.
     */
    public String getLoggedUserName() {
        return currentUser != null ? currentUser.usernameProperty() : null;
    }

    /**
     * Gets the ID of the currently logged-in user.
     *
     * @return the ID of the logged-in user, or null if no user is logged in.
     */
    public int getLoggedUserId() {
        return currentUser != null ? currentUser.getId() : null;
    }

    /**
     * Creates a new author in the database.
     *
     * @param fName    the first name of the author.
     * @param lastName the last name of the author.
     * @param email    the email of the author.
     * @param city     the city of the author.
     */
    public void createAuthor(String fName, String lastName, String email, String city) {
        authorDAO.create(fName, lastName, email, city);
    }

    /**
     * Updates an existing author in the database.
     *
     * @param author the updated Author object.
     */
    public void updateAuthor(Author author) {
        authorDAO.update(author);
    }

    /**
     * Deletes an author from the database by their ID.
     *
     * @param id the ID of the author to delete.
     */
    public void deleteAuthor(int id) {
        authorDAO.delete(id);
    }

    /**
     * Retrieves all authors from the database and returns them as an ObservableList.
     *
     * @return an ObservableList of all Author objects.
     */
    public ObservableList<Author> getAuthors() {
        System.out.println(authorDAO.findAll());
        return authorDAO.findAll();
    }
}
