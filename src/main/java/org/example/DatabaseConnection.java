package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String URL = "jdbc:postgresql://localhost:5432/coworking_space";
    private static String NAME = "postgres";
    private static String PASSWORD = "Admin";

    public static Connection connect(){
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        }catch(SQLException e){
            System.out.println("Xeta: "+ e.getMessage());
        }
        return conn;
    }
}
