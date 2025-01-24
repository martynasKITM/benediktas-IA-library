package com.benedict.library.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a user in the system.
 *
 * The `User` class models a user with a unique identifier and a username. It provides getter methods to retrieve the username and the user ID.
 * The `username` is stored as a JavaFX `StringProperty` to allow for data binding in the UI.
 */
public class User {
    // Unique identifier for the user
    private int id;

    // Username property for the user, allows for data binding
    private StringProperty username;

    /**
     * Constructor that initializes a user with the given ID and username.
     *
     * @param id the unique identifier for the user
     * @param username the username of the user
     */
    public User(int id, String username) {
        this.id = id;
        this.username = new SimpleStringProperty(username);
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user as a string
     */
    public String usernameProperty() {
        return username.get();
    }

    /**
     * Gets the ID of the user.
     *
     * @return the unique ID of the user
     */
    public int getId() {
        return id;
    }
}
