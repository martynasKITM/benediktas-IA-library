/**
 * Package declaration for the DAO (Data Access Object) classes.
 */
package com.benedict.library.dao;

import com.benedict.library.Models.Author;
import com.benedict.library.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * DAO class for managing Author data.
 * Implements the GenericDAO interface to provide basic CRUD operations for Authors.
 */
public class AuthorDAO implements GenericDAO {

    private Connection conn;
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    /**
     * Constructor to initialize the AuthorDAO with a database connection.
     *
     * @param conn the Connection object used for interacting with the database.
     */
    public AuthorDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Creates a new author in the database.
     *
     * @param fName    the first name of the author.
     * @param lastName the last name of the author.
     * @param email    the email of the author.
     * @param city     the city of the author.
     */
    @Override
    public void create(String fName, String lastName, String email, String city) {
        String sql = "INSERT INTO Authors (FirstName, LastName, Email, City, Date, User_id) VALUES (?, ?, ?, ?, ?, ?)";
        int userId = Model.getInstance().getLoggedUserId();
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, fName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, city);
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.setInt(6, userId);
            stmt.executeUpdate();
            logger.info("Author successfully created.");
        } catch (SQLException e) {
            logger.severe("Error creating author: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Finds an author by their ID. Not yet implemented.
     *
     * @param id the ID of the author to be found.
     * @return an Object representing the found author or null if not found.
     */
    @Override
    public Object findById(int id) {
        return null;
    }

    /**
     * Updates an existing author in the database.
     *
     * @param entity an object representing the Author to be updated. Must be an instance of Author.
     * @throws IllegalArgumentException if the entity is not an instance of Author.
     */
    @Override
    public void update(Object entity) {
        if (!(entity instanceof Author)) {
            throw new IllegalArgumentException("Expected an Author object");
        }
        Author author = (Author) entity;
        String sql = "UPDATE Authors SET FirstName = ?, LastName = ?, Email = ?, City = ? WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            stmt.setString(3, author.getEmail());
            stmt.setString(4, author.getCity());
            stmt.setInt(5, author.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                logger.info("Author updated successfully: " + author);
            } else {
                logger.warning("No author found with id: " + author.getId());
            }
        } catch (SQLException e) {
            logger.severe("Error updating author: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Deletes an author from the database by their ID.
     *
     * @param id the ID of the author to be deleted.
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Authors WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Author with ID " + id + " was deleted successfully.");
            } else {
                logger.warning("No author found with ID " + id + " to delete.");
            }
        } catch (SQLException e) {
            logger.severe("Error deleting author with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all authors from the database.
     *
     * @return an ObservableList of Author objects representing all authors in the database.
     */
    @Override
    public ObservableList<Author> findAll() {
        ObservableList<Author> authors = FXCollections.observableArrayList();
        String sql = "SELECT id, FirstName, LastName, Email, City FROM Authors";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                String city = rs.getString("City");

                Author author = new Author(id, firstName, lastName, email, city);
                authors.add(author);
            }
        } catch (SQLException e) {
            logger.severe("Error fetching authors: " + e.getMessage());
            e.printStackTrace();
        }
        return authors;
    }
}
