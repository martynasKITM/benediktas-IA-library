package com.benedict.library.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Manages the connection to the SQLite database.
 *
 * The `DatabaseDriver` class is responsible for establishing and closing a connection to the SQLite database, which stores the data for the application.
 * It uses the JDBC API to interact with the database and logs any connection issues using Java's logging mechanism.
 */
public class DatabaseDriver {
    // Database connection instance
    private Connection conn;

    // Logger for error and connection management messages
    private static final Logger logger = Logger.getLogger(DatabaseDriver.class.getName());

    /**
     * Constructor that initializes the database connection.
     * It tries to establish a connection to the SQLite database file "library.db".
     * If the connection fails, an error message is logged.
     */
    public DatabaseDriver() {
        try {
            // Establishing connection to the SQLite database
            this.conn = DriverManager.getConnection("jdbc:sqlite:library.db");
        } catch (SQLException e) {
            // Logging the error if the connection fails
            logger.severe("Negaliu prisijungti prie DB: " + e.getMessage());
        }
    }

    /**
     * Gets the current database connection.
     *
     * @return the database connection object
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Closes the current database connection.
     * If the connection is already closed or null, no action is taken. If there is an error during closing,
     * the error message is logged.
     */
    public void closeConnection() {
        try {
            // Closing the connection if it's not already closed
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Logging any error encountered while closing the connection
            logger.severe("Error closing database connection: " + e.getMessage());
        }
    }
}
