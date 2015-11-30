/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyhotels;

import Entities.User;
import Entities.Customer;
import Entities.Manager;
import Entities.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
/**
 *
 * @author pwilson3
 */
public class FancyHotelSingleton {
    private static FancyHotelSingleton instance = null;
    private static User currentUser;
    private static Customer currentCustomer;
    private static Manager currentManager;
    private static Connection conn;
    private static String databaseName;
    
    protected FancyHotelSingleton() {
        // Exists only to defeat instantiation
    }
    
    /**
     * Use to access the singleton
     * @return sing FancyHotelSingleton object with current details
     */
    public static FancyHotelSingleton getInstance() {
        if(instance == null) {
            instance = new FancyHotelSingleton();
            databaseName = "cs4400_Group_61";
            conn = null;
            
        }
        return instance;
    }
    
    /**
     * @return current customer object
     */
    public Customer getCustomer() {
        return currentCustomer;
    }
    
    /**
     * @return current manager object
     */
    public Manager getManager() {
        return currentManager;
    }
    
    /**
     * Attempt to connect to the MySQL database
     * @return status of connection
     */
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
    
    /**
     * Attempt to create customer account with username, password and email 
     * Returns status of account creation
     * @param username username for user
     * @param password Password for user
     * @param email Email address for user
     * @return status of account creation
     * @throws SQLException in the case invalid SQL command
     */
    public static boolean createUser(String username, String password, 
            String email) throws SQLException {
        
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String s = String.format("insert into %s .CUSTOMER "
                    + "(Username, Password, Email) values "
                    + "(\"C%s\", \"%s\", \"%s\")", 
                    databaseName, username, password, email);
            stmt.executeUpdate(s);
            
            if (stmt != null) { 
                stmt.close();    
            } 
            return true;
            
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean cancelReservation(String reserv_id) {
        boolean status = false;
        
        
        return status;
    }
    
    /**
     * Finds all of the rooms that fit the requested parameters
     * @param loc The location of the room
     * @param start_date Date of beginning of query
     * @param end_date Date of end of query
     * @return array of Room objects
     * @throws SQLException in the case invalid SQL command
     */
    public static Room[] findRooms(String loc, String start_date, String end_date) 
            throws SQLException {
        //TODO
        Room [] rooms;
        return null;

    }
    
    /**
     * Attempts to login with the credentials
     * If success, sets the current user/manager/customer to properties
     * @param username username for user
     * @param password Password for user
     * @return c user is a customer
     * @return m user is a manger
     * @return null user does not exist
     * @throws SQLException in the case invalid SQL command
     */
    public static String login(String username, String password) 
            throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String s = String.format("select Username, Password, Email from "
                    + "%s .CUSTOMER where Username=\"%s\" and Password=\"%s\"", 
                    databaseName, username, password);
            
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                // Determine if customer or manager
                String uname = rs.getString("Username");
                String type = uname.substring(0, 1);
                currentUser = new User(uname, password);
                
                if (type.equals("C") || type.equals("c")) {
                    // User type customer
                    currentCustomer = new Customer(uname, rs.getString("Password"), rs.getString("Email"));
                    if (stmt != null) { 
                        stmt.close();
                    } 
                    return "c";
                } else if (type.equals("M") || type.equals("m")) {
                    // User type management
                    currentManager = new Manager(uname, rs.getString("Password"));
                    if (stmt != null) { 
                        stmt.close();
                    } 
                    return "m";
                }   
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return null;
        } 
        return null;
    }
    
    public static boolean makeReservation(String username, int[] room_ids, String start_date, String end_date) {
        //TODO
        return false;
    }
    
    public static boolean addPayment(String cardName, String cardNum, Date expDate, String cvv) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String s = String.format("insert into %s .PAYMENT_INFORMATION "
                    + "(Username, Name, Exp_Date, CVV, Card_number) values "
                    + "(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", 
                    databaseName, currentUser.getUsername(), 
                    cardName, expDate.toString(), cvv);
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
}
