package com.benedict.library.dao;

import com.benedict.library.Models.User;
import com.benedict.library.Utilities.UserAuthUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * Data Access Object (DAO) for interacting with the "Users" table in the database.
 *
 * This class provides methods for managing user data, including operations to check if a user exists,
 * retrieve a user by credentials (username and password), count users, and create a new user.
 * The methods interact with the database to perform CRUD operations related to users.
 */
public class UserDAO {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    /**
     * Constructor for UserDAO that initializes the database connection.
     *
     * @param conn the database connection to be used by the DAO
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Finds a user by their credentials (username and password).
     *
     * @param userName the username of the user
     * @param password the password of the user
     * @return a User object if the credentials are valid, null if not
     */
    public User findUserByCredentials(String userName, String password) {
        ResultSet resultSet = null;
        User user = null;
        String sql = "SELECT id, UserName, Password FROM Users WHERE UserName = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String storedPasswordHash = resultSet.getString("Password");
                if (UserAuthUtils.verifyPassword(password, storedPasswordHash)) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("UserName"));
                }
            }
        } catch (SQLException e) {
            logger.severe("Database error while finding user by credentials: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Counts the total number of users in the "Users" table.
     *
     * @return the total count of users
     */
    public int countUsers() {
        int userCount = 0;
        String sql = "SELECT COUNT(*) AS user_count FROM Users";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {
            if (resultSet.next()) {
                userCount = resultSet.getInt("user_count");
                logger.info("User count: " + userCount);
            }
        } catch (SQLException e) {
            logger.severe("Error counting users: " + e.getMessage());
        }
        return userCount;
    }

    /**
     * Checks if a user exists in the database by their username.
     *
     * @param userName the username to check
     * @return true if the user exists, false otherwise
     */
    public boolean isUserExist(String userName) {
        String sql = "SELECT COUNT(*) AS user_count FROM Users WHERE UserName = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("user_count") > 0;
            }
        } catch (SQLException e) {
            logger.severe("Error checking if user exists: " + e.getMessage());
        }
        return false;
    }

    /**
     * Creates a new user in the "Users" table with the specified username, password, and registration date.
     *
     * @param userName the username of the new user
     * @param password the password of the new user
     * @param date the registration date of the new user
     */
    public void createUser(String userName, String password, LocalDate date) {
        String sql = "INSERT INTO Users (UserName, Password, Date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.setString(2, UserAuthUtils.hashPassword(password)); // Hash the password before saving
            stmt.setDate(3, Date.valueOf(date));
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error creating user: " + e.getMessage());
        }
    }
}
