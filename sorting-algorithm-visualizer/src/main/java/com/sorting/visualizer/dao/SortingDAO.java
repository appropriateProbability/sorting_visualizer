package com.sorting.visualizer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class SortingDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/sorting_db";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void saveSortingResult(String inputData, String sortedData) {
        String sql = "INSERT INTO sorting_results (input_data, sorted_data) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inputData);
            stmt.setString(2, sortedData);
            stmt.executeUpdate();
            System.out.println("Sorting result saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error saving sorting result: " + e.getMessage());
        }
    }
}
