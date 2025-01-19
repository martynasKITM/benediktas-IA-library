/*package com.benedict.minibank.Services;
import com.benedict.minibank.Models.DatabaseDriver;

import java.sql.*;
import java.time.LocalDate;

public class AuthorDAO {
    private final DatabaseDriver databaseDriver;

    public AuthorDAO(DatabaseDriver databaseDriver) {
        this.databaseDriver = databaseDriver;
    }

    public boolean createAuthor(String firstName, String lastName, String email, String city, LocalDate date) {
        String sql = "INSERT INTO Authors (FirstName, LastName, Email, City, Date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = databaseDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, city);
            stmt.setDate(5, Date.valueOf(date));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getAuthorByEmail(String email) {
        String sql = "SELECT * FROM Authors WHERE Email = ?";
        try (Connection conn = databaseDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteAuthorByEmail(String email) {
        String sql = "DELETE FROM Authors WHERE Email = ?";
        try (Connection conn = databaseDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}*/

