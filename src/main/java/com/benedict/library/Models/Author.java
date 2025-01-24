package com.benedict.library.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents an Author in the system.
 *
 * The `Author` class encapsulates an author's details such as first name, last name, email, and city.
 * It uses JavaFX properties to allow for data binding with UI components, making it suitable for use in a JavaFX application.
 * This class provides getter and setter methods for all properties, as well as `toString()` method for displaying the author's information.
 */
public class Author {
    // Properties for storing author information
    private IntegerProperty id;        // Author's ID
    private StringProperty firstName;  // Author's first name
    private StringProperty lastName;   // Author's last name
    private StringProperty email;      // Author's email address
    private StringProperty city;       // Author's city of residence

    /**
     * Constructs an Author object with the specified parameters.
     *
     * @param id the ID of the author
     * @param firstName the first name of the author
     * @param lastName the last name of the author
     * @param email the email address of the author
     * @param city the city of residence of the author
     */
    public Author(int id, String firstName, String lastName, String email, String city){
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.city = new SimpleStringProperty(city);
    }

    /**
     * Gets the ID of the author.
     *
     * @return the author's ID
     */
    public int getId(){
        return id.get();
    }

    /**
     * Gets the first name of the author.
     *
     * @return the author's first name
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Returns a StringProperty representing the author's first name.
     *
     * @return the first name property
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * Sets the ID of the author.
     *
     * @param generatedId the ID to set for the author
     */
    public void setId(int generatedId){
        this.id.set(generatedId);
    }

    /**
     * Sets the first name of the author.
     *
     * @param firstName the first name to set for the author
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * Gets the last name of the author.
     *
     * @return the author's last name
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Returns a StringProperty representing the author's last name.
     *
     * @return the last name property
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * Sets the last name of the author.
     *
     * @param lastName the last name to set for the author
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * Gets the email of the author.
     *
     * @return the author's email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * Returns a StringProperty representing the author's email.
     *
     * @return the email property
     */
    public StringProperty emailProperty() {
        return email;
    }

    /**
     * Sets the email address of the author.
     *
     * @param email the email address to set for the author
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Gets the city of residence of the author.
     *
     * @return the author's city of residence
     */
    public String getCity() {
        return city.get();
    }

    /**
     * Returns a StringProperty representing the author's city of residence.
     *
     * @return the city property
     */
    public StringProperty cityProperty() {
        return city;
    }

    /**
     * Sets the city of residence of the author.
     *
     * @param city the city to set for the author
     */
    public void setCity(String city) {
        this.city.set(city);
    }

    /**
     * Returns a string representation of the Author object.
     *
     * @return a string containing the author's details
     */
    @Override
    public String toString() {
        return String.format("Author [FirstName=%s, LastName=%s, Email=%s, City=%s]",
                getFirstName(), getLastName(), getEmail(), getCity());
    }
}
