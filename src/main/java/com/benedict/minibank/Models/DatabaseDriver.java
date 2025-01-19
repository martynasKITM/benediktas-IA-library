package com.benedict.minibank.Models;

import com.benedict.minibank.App;
import com.benedict.minibank.Utilities.UserAuthUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Logger;

public class DatabaseDriver {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:library.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        String url = "jdbc:sqlite:library.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error occurred in connect method: " + e.getMessage());
        }
        return conn;
    }

    /*
     * User section
     */

    public User findUserByCredentials(String userName, String password) {
        ResultSet resultSet = null;
        User user = null;
        String sql = "SELECT Name, Password FROM Users WHERE Name = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String storedPasswordHash = resultSet.getString("Password");
                if (UserAuthUtils.verifyPassword(password, storedPasswordHash)) {
                    user = new User(resultSet.getString("Name"));
                }
            }
        } catch (SQLException e) {
            logger.info("Database error");
            e.printStackTrace();
        }
        return user;
    }

    public void createClient(String fName, String lName, String pAddress, String password, LocalDate date) {
        String sql = "INSERT INTO Clients (FirstName, LastName, PayeeAddress, Password, Date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, pAddress);
            stmt.setString(4, password);
            stmt.setDate(5, java.sql.Date.valueOf(date));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getAllClientsData() {
        String sql = "SELECT * FROM Clients";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getLastClientId() {
        String sql = "SELECT seq FROM sqlite_sequence WHERE name = 'Clients'";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("seq");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /* Athors */

    public void createAuthor(String fName, String lName, String email, String city, LocalDate date) {
        String sql = "INSERT INTO Authors (FirstName, LastName, Email, City, Date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, email);
            stmt.setString(4, city);
            stmt.setDate(5, java.sql.Date.valueOf(date));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
