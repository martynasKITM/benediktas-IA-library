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
        String sql = "SELECT UserName, Password FROM Users WHERE UserName = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String storedPasswordHash = resultSet.getString("Password");
                if (UserAuthUtils.verifyPassword(password, storedPasswordHash)) {
                    user = new User(resultSet.getString("UserName"));
                }
            }
        } catch (SQLException e) {
            logger.info("Database error");
            e.printStackTrace();
        }
        return user;
    }

    public int countUsers() {
        int userCount = 0;
        String sql = "SELECT COUNT(*) AS user_count FROM Users";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                userCount = resultSet.getInt("user_count");
                System.out.println("from db: " + userCount);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging in production
        }
        return userCount;
    }

  //Check if user Exist

    public boolean isUserExist(String userName) {
        String sql = "SELECT COUNT(*) AS user_count FROM Users WHERE UserName = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("user_count") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createUser(String userName, String password, LocalDate date) {
        String sql = "INSERT INTO Users (UserName, Password, Date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.setString(2, UserAuthUtils.hashPassword(password));
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
