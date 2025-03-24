package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    public static List<CoworkingSpace> getAvailableSpaces() {
        List<CoworkingSpace> spaceList = new ArrayList<>();
        String sql = "SELECT * FROM workspaces";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                CoworkingSpace space = new CoworkingSpace(
                        rs.getInt("space_id"),
                        rs.getString("space_type"),
                        rs.getDouble("price_per_hour"),
                        rs.getBoolean("availability_status")
                );
                spaceList.add(space);
            }
        } catch (SQLException e) {
            System.out.println("Error accessing spaces: " + e.getMessage());
        }
        return spaceList;
    }


    public static boolean insertReservation(Reservation res) {
        String sql = "INSERT INTO reservations (booking_id, customer_name, date, start_time, end_time, space_id) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, res.bookingID);
            pstmt.setString(2, res.customerName);
            pstmt.setString(3, res.date);
            pstmt.setString(4, res.startTime);
            pstmt.setString(5, res.endTime);
            pstmt.setInt(6, res.spaceID);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding reservation: " + e.getMessage());
            return false;
        }
    }

    public static void setSpaceBooked(int spaceID){
        String sql = "UPDATE workspaces SET availability_status = false WHERE space_id = ?";

        try(Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){

            pstmt.setInt(1, spaceID);
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error updating space availability: " +e.getMessage());
        }
    }

    public static void setSpaceAvailable(int spaceID){
        String sql = "UPDATE workspaces SET availability_status = true WHERE space_id = ?";

        try(Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setInt(1, spaceID);

            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error updating space availability: " +e.getMessage());
        }
    }



    public static boolean deleteBooking(int bookingID) {
        String sql = "DELETE FROM reservations WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingID);
            int rowCount = pstmt.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            System.out.println("Error cancelling reservation: " + e.getMessage());
            return false;
        }
    }

    public static List<Reservation> getMyBooking(){
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try(Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ){
            while(rs.next()){
                Reservation bookingList = new Reservation(
                        rs.getInt("booking_id"),
                        rs.getString("customer_name"),
                        rs.getString("date"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getInt("space_id")
                );
                reservations.add(bookingList);
            }
        }catch (SQLException e){
            System.out.println("Error accessing bookings: ");
        }
        return reservations;
    }
}
