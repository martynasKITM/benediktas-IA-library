package com.benedict.minibank.Services;

/*import com.benedict.minibank.Models.Client;
import com.benedict.minibank.Models.DatabaseDriver;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final DatabaseDriver databaseDriver;

    public UserDAO(DatabaseDriver databaseDriver) {
        this.databaseDriver = databaseDriver;
    }

    public boolean createClient(String firstName, String lastName, String payeeAddress, String password, LocalDate date) {
        String sql = "INSERT INTO Clients (FirstName, LastName, PayeeAddress, Password, Date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = databaseDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, payeeAddress);
            stmt.setString(4, password);
            stmt.setDate(5, Date.valueOf(date));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Client> getClientData(String pAddress, String password) {
        List<Client> clientList = new ArrayList<>();
        String query = "SELECT * FROM clients WHERE PayeeAddress = ? AND Password = ?";

        try (Connection conn = databaseDriver.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, pAddress);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String payeeAddress = rs.getString("PayeeAddress");
                    String dateCreated = rs.getString("Date");

                    // Create Client object and add to the list
                    Client client = new Client(firstName, lastName, payeeAddress, null, null, dateCreated);
                    clientList.add(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientList;
    }


    public List<String> getAllClients() {
        String sql = "SELECT * FROM Clients";
        List<String> clients = new ArrayList<>();
        try (Connection conn = databaseDriver.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(rs.getString("FirstName") + " " + rs.getString("LastName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
*/