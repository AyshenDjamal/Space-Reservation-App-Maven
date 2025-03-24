package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceRepository {

    public static boolean addSpace(CoworkingSpace space) {
        String sql = "INSERT INTO workspaces (space_id, space_type, price_per_hour, availability_status) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, space.getSpaceID());
            pstmt.setString(2, space.getSpaceType());
            pstmt.setDouble(3, space.getPricePerHour());
            pstmt.setBoolean(4, space.getIsAvailable());

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error adding workspace: " + e.getMessage());
            return false;
        }
    }


    public static boolean removeSpace(int id) {
        String sql = "DELETE FROM workspaces WHERE space_id = ?";
        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);

            int rowCount = pstmt.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            System.out.println("Error removing");
        }
        return false;
    }



    public static List<Reservation> getAllBookings() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                Reservation res = new Reservation(
                rs.getInt("booking_id"),
                rs.getString("customer_name"),
                rs.getString("date"),
                rs.getString("start_time"),
                rs.getString("end_time"),
                rs.getInt("space_id")
);
                reservations.add(res);
            }
        } catch (SQLException e) {
            System.out.println("Error accessing bookings: " + e.getMessage());
        }
        return reservations;
    }
}

