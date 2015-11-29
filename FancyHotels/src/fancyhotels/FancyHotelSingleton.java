/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyhotels;

import Entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pwilson3
 */
public class FancyHotelSingleton {
    private static FancyHotelSingleton instance = null;
    public static User currentUser;
    public static Connection conn;
    public static String databaseName;
    
    protected FancyHotelSingleton() {
        // Exists only to defeat instantiation
    }
    public static FancyHotelSingleton getInstance() {
        if(instance == null) {
            instance = new FancyHotelSingleton();
            databaseName = "cs4400_Group_61";
            conn = null;
        }
        return instance;
    }
    
    public static boolean connectToDB() throws SQLException {
        conn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Group_61", "cs4400_Group_61", "vswEJJqe");
            
            if(!conn.isClosed()) {
                System.out.println("Successfully connected to MySQL server using TCP/IP...");
                return true;
            } else {
                System.out.println("Unable to connect to MySQL server using TCP/IP...");
                return false;
            }
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean createUser(String username, String password, 
            String email) throws SQLException {
        
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String s = String.format("insert into %s .CUSTOMER "
                    + "(Username, Password, Email) values "
                    + "(\"C%s\", \"%s\", \"%s\")", databaseName, username, 
                    password, email);
            stmt.executeUpdate(s);
            
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            if (stmt != null) { 
                stmt.close();
                return true;
            } else {
                return false;
            }
        }
    }
    
    public static boolean cancelReservation(String reserv_id) {
        boolean status = false;
        
        
        return status;
    }
    
    public static int[] findRooms(String loc, String start_date, String end_date) {
        //TODO
        int [] A = {1, 2, 3};
        return A;
    }
    
    public static boolean login(String username, String password) {
        //TODO
        return false;
    }
    
    public static boolean makeReservation(String username, int[] room_ids, String start_date, String end_date) {
        //TODO
        return false;
    }
    
    public static boolean addPayment(String cardName, String cardNum, String expMo, String expYr, String cvv) {
        //Todo: 
        return false;
    }
}
