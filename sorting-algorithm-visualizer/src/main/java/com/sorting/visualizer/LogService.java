package com.sorting.visualizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogService {
    public static void addSortingLog(String algorithm, double timeTaken) {
        String sql = "INSERT INTO sorting_logs (algorithm, time_taken) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, algorithm);
            stmt.setDouble(2, timeTaken);
            stmt.executeUpdate();
            System.out.println("Log added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
