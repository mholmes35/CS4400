/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyhotels;

import Entities.*;
//import Entities.Customer;
//import Entities.Manager;
//import Entities.Room;
//import Entities.HotelReview;

import Entities.User;
import Entities.Customer;
import Entities.Manager;
import Entities.Room;
import Entities.HotelReview;
import Entities.PaymentInformation;
import Entities.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.SimpleDateFormat;
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
    
    public void setCustomer(Customer cust){
        currentCustomer = cust;
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
     * @return connection object
     */
    public Connection getConnection() {
        return conn;
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
            String s;
            String type = username.substring(0, 1);
            if (type.equals("C") || type.equals("c")) {
                s = String.format("select Username, Password, Email from "
                    + "%s .CUSTOMER where Username=\"%s\" and Password=\"%s\"", 
                    databaseName, username, password);
            } else if (type.equals("M") || type.equals("m")) {
                s = String.format("select Username, Password from "
                    + "%s .MANAGER where Username=\"%s\" and Password=\"%s\"",
                    databaseName, username, password);
            } else {
                return null;
            }
            
            
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                // Determine if customer or manager
                String uname = rs.getString("Username");
                
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
    
    public static String makeReservation(ArrayList<Room> finalRooms, String start_date, String end_date, String cardNum, Float totalCost) {
        //TODO
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            //create reservation
            String s = String.format("insert into %s .RESERVATION "
                    + "(Start_Date, End_Date, Is_Cancelled, Total_Cost, Card_Number) values "
                    + "(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", 
                    databaseName, start_date, end_date, 
                    "0", totalCost, cardNum);
            stmt.executeUpdate(s, Statement.RETURN_GENERATED_KEYS);
            //add has relationship
            rs = stmt.getGeneratedKeys();
            String confirmationID = "";
            if (rs != null){
                rs.next();
                confirmationID = rs.getString(1);
            }
            for (Room curr : finalRooms){  
                Integer currentRoom = curr.getRoomNumber();
                String loc = curr.getLocation();
                Boolean extraBed = curr.isExtraBed();
                Integer ex = (extraBed) ? 1:0; 
                s = String.format("insert into %s .HAS (ReservationID, Room_Number"
                        + ", Location, Extra_Bed) values (\"%s\", \"%s\", \"%s\", "
                        + "\"%s\")", databaseName, confirmationID, currentRoom.toString(), loc, ex.toString());
                stmt.executeUpdate(s);
            }
            if (stmt != null) { 
                stmt.close();    
            } 
            return confirmationID;
            
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Adds a payment object for the current user
     * @param cardName Name on the card
     * @param cardNum Card Number
     * @param expDate Date object of when the card expires
     * @param cvv String of CVV code on back of card
     * @return status of adding payment option
     * @throws SQLException in the case invalid SQL command
     */
    public static boolean addPayment(String cardName, String cardNum, 
            String expDate, String cvv) 
            throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String s = String.format("insert into %s .PAYMENT_INFORMATION "
                    + "(Username, Name, Exp_Date, CVV, Card_number) values "
                    + "(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", 
                    databaseName, currentUser.getUsername(), 
                    cardName, expDate, cvv, cardNum);

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
    
    /**
     * Finds all credit cards associated with the user
     * @param uname the username for the query     
     * @return array of Room objects
     * @throws SQLException in the case invalid SQL command
     */
    public static ArrayList<PaymentInformation> getPaymentInformation() 
            throws SQLException {
        Statement stmt = null;
        ArrayList<PaymentInformation> options = new ArrayList<PaymentInformation>();
        String uname = currentCustomer.getUsername();
        
        try {
           stmt = conn.createStatement();
           String s = String.format("select * from  %s .PAYMENT_INFORMATION "
                    + "where Username=\"%s\"", 
                    databaseName, uname);
                  
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                String name = rs.getString("Name");
                String exp_date = rs.getDate("Exp_date").toString();
                int cvv = rs.getInt("CVV");
                String c_num = rs.getString("Card_number");
                
                options.add(new PaymentInformation(name, exp_date, cvv, c_num, 
                        uname));
                
            }
            if (stmt != null) { 
                stmt.close();
                
            } 
            return options;
            
        } catch (SQLException e) {
            System.err.println("Find Rooms \nException: " + e.getMessage());
            return null;
        } 

    }

    /**
     * Creates a review on a hotel 
     * @param comment Comment for the review
     * @param rating Rating for the review
     * @param location Location of the hotel for the review
     * @return status of creating a review
     * @throws SQLException in the case invalid SQL command
     */
    public static boolean createReview(String comment, String rating, 
            String location) 
            throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String s = String.format("insert into %s .HOTEL_REVIEW "
                    + "(Username, Comment, Rating, Location) values "
                    + "(\"%s\", \"%s\", \"%s\", \"%s\")", 
                    databaseName, currentCustomer.getUsername(), 
                    comment, rating, location);
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
    
    /**
     * Finds all of the rooms that fit the requested parameters
     * @param loc The location of the room
     * @param start_date Date of beginning of query
     * @param end_date Date of end of query
     * @return array of Room objects
     * @throws SQLException in the case invalid SQL command
     */
    public static ArrayList<Room> findRooms(String loc, String start_date, String end_date) 
            throws SQLException {
        Statement stmt = null;
        ArrayList<Room> rooms = new ArrayList<Room>();
        
        try {
           stmt = conn.createStatement();
                  
            String x = String.format("Select * from ROOM where Room_Number not "
                    + "in (Select Room_number from (select * from ROOM natural"
                    + " join RESERVATION natural join HAS) y Where Location = "
                    + "\"%s\" and not Is_Cancelled and (Start_Date between \"%s\""
                    + " and \"%s\" or \"%s\" between Start_Date and End_Date"
                    + " or \"%s\" between Start_Date and End_Date )) and Location"
                    + " = \"%s\""
                    ,loc,start_date,end_date,start_date,end_date,loc);
            
            ResultSet rs = stmt.executeQuery(x);
            while (rs.next()) {
                int numPeople = rs.getInt("Number_of_people");
                String category = rs.getString("Room_category");
                Float cPD = rs.getFloat("Cost_per_day");
                int roomNum = rs.getInt("Room_Number");
                rooms.add(new Room(numPeople, category, cPD, roomNum, loc,
                        false, 10.00f));
                
            }
            if (stmt != null) { 
                stmt.close();
                
            } 
            return rooms;
            
        } catch (SQLException e) {
            System.err.println("Find Rooms \nException: " + e.getMessage());
            return null;
        }
    }
    
    public static ArrayList<Room> findUpdatedRooms(String start_date, 
            String end_date, int rID) 
            throws SQLException {
        
        Statement stmt = null;
        ArrayList<Room> rooms = new ArrayList<Room>();
        
        try {
           stmt = conn.createStatement();
                  
            String x = String.format("Select * from ROOM natural join HAS "
                    + "natural join RESERVATION where ReservationID = %d "
                    + "and not exists\n (Select * from\n "
                    + "(select * from ROOM natural join RESERVATION "
                    + "natural join HAS Where not Is_Cancelled) y\n "
                    + "where Room_number in (select Room_number from HAS "
                    + "natural join RESERVATION where ReservationID = %d "
                    + "and y.Location = Location)\n and "
                    + "(Start_Date between \"%s\" and \"%s\" "
                    + "or \"%s\" between Start_Date and End_Date "
                    + "or \"%s\" between Start_Date and End_Date)\n "
                    + "and ReservationID <> %d);", 
                    rID,rID,start_date, end_date, start_date, end_date, rID);
            System.out.println(x);
            ResultSet rs = stmt.executeQuery(x);
            while (rs.next()) {
                int numPeople = rs.getInt("Number_of_people");
                String category = rs.getString("Room_category");
                Float cPD = rs.getFloat("Cost_per_day");
                int roomNum = rs.getInt("Room_Number");
                String loc = rs.getString("Location");
                rooms.add(new Room(numPeople, category, cPD, roomNum, loc,
                        false, 10.00f));
                
            }
            if (stmt != null) { 
                stmt.close();
                
            } 
            return rooms;
            
        } catch (SQLException e) {
            System.err.println("Find Rooms \nException: " + e.getMessage());
            return null;
        }
    
    }
    
    public static float countDays(String startDate, String endDate){
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         try{
            java.util.Date start = format.parse(startDate);
            java.util.Date end = format.parse(endDate);
            int days = (end.getDate() - start.getDate()) + 1;
            return (float)days;
         } catch (Exception e){
               System.out.print("Error: " + e);
               return 0.0f;
         }
         
    }
        public static boolean isValid(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         try{
            java.util.Date start = format.parse(startDate);
            java.util.Date end = format.parse(endDate);
            java.util.Date now = format.parse("2015-12-04");
            int startDiff = start.compareTo(now);
            int endDiff = end.compareTo(now);
            boolean isPast = (endDiff<0 && startDiff <0);
            int compEnd = end.compareTo(start);
            boolean isNotChronological = compEnd <0;
            boolean isSameDay = (compEnd==0);
            
            return(isPast || isNotChronological || isSameDay);
            
         } catch (Exception e){
               System.out.print("Error: " + e);
               return false;
         }
    }
    
    public static ArrayList<Entities.PaymentInformation> getUserCards(){
        ArrayList<Entities.PaymentInformation> cards = new ArrayList<Entities.PaymentInformation>();
        Statement stmt = null;
        String user = currentCustomer.getUsername();
        try {
           stmt = conn.createStatement();
           String s = String.format("SELECT * FROM cs4400_Group_61.PAYMENT_INFO"
                   + "RMATION WHERE PAYMENT_INFORMATION.Username = \"%s\"",user);
                  
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                
                String cardName = rs.getString("Name");
                String exp = rs.getDate("Exp_date").toString();
                int cvv = rs.getInt("CVV");
                String cardNum = rs.getString("Card_number");
                cards.add(new Entities.PaymentInformation(cardName, exp, cvv, cardNum, user));
                
            }
            if (stmt != null) { 
                stmt.close();
                
            } 
            return cards;
            
        } catch (SQLException e) {
            System.err.println("Payment info \nException: " + e.getMessage());
            return null;
        }
        
    }

    /**
     * Retrieves hotel reviews based on location
     * @param location Location of the hotel for the review
     * @return ArrayList of HotelReview Objects
     * @throws SQLException in the case invalid SQL command
     */
    public static ArrayList<HotelReview> getReviews(String location) 
            throws SQLException {
        Statement stmt = null;
        ArrayList<HotelReview> reviews = new ArrayList<HotelReview>();
        
        try {
            stmt = conn.createStatement();
            String s = String.format("select * from  %s .HOTEL_REVIEW "
                    + "where Location=\"%s\"", 
                    databaseName, location);

            
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                String uname = rs.getString("Username");
                String comment = rs.getString("Comment");
                String rating = rs.getString("Rating");
                int rev_num = rs.getInt("Review_number");
                reviews.add(new HotelReview(uname, comment, rating, location, rev_num));
                
            }
            if (stmt != null) { 
                stmt.close();
                
            } 
            return reviews;
            
        } catch (SQLException e) {
            System.err.println("getReviews \nException: " + e.getMessage());
            return null;
        } 
    }
    
    /**
     * Retrieves reservations based on reservation_id
     * @param reservation_id 
     * @return reservation object if found, otherwise null
     * @throws SQLException in the case invalid SQL command
     */
    public static Reservation getReservation(int reservation_id) 
            throws SQLException {
        Statement stmt = null;
                
        try {
            Reservation r = null;
            stmt = conn.createStatement();
            String s = String.format("select * from  %s .RESERVATION "
                    + "where ReservationID=%d", 
                    databaseName, reservation_id);

            
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                Date sDate = rs.getDate("Start_Date");
                Date eDate = rs.getDate("End_Date");
                int isCancelled = rs.getInt("Is_Cancelled");
                float totalCost = rs.getFloat("Total_Cost");
                String cNum = rs.getString("Card_Number");
                
                r = new Reservation(sDate, eDate, isCancelled==1, totalCost, cNum, currentCustomer.getUsername());
                r.setReservationID(reservation_id);
                
            }
            if (stmt != null) { 
                stmt.close();
            } 
             return r;
            
        } catch (SQLException e) {
            System.err.println("Get Reservation Exception: " + e.getMessage());
            return null;
        } 
    }
    
    public static ArrayList<HashMap> getRevenueReport() throws SQLException {
        Statement stmt = null;
                
        try {
            ArrayList<HashMap> results = new ArrayList<HashMap>();
            stmt = conn.createStatement();
            String s = String.format("select MONTH(Start_Date) as mon, Location, SUM(Total_Cost) as totalCost from HAS Natural Join RESERVATION \n" +
                "where not Is_Cancelled\n" +
                "group by Location, mon \n" +
                "order by mon;");
                

            
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                HashMap hm = new HashMap();
                
                int mon = rs.getInt("mon");
                String loc = rs.getString("Location");
                int cost = rs.getInt("totalCost");
                
                hm.put("Month", new Integer(mon));
                hm.put("Location", loc);
                hm.put("Cost", new Integer(cost));
                        
                results.add(hm);
                
            }
            
            if (stmt != null) { 
                stmt.close();
            } 
             return results;
            
        } catch (SQLException e) {
            System.err.println("Get Revenue Report Exception: " + e.getMessage());
            return null;
        } 
    }
    
    public static ArrayList<HashMap> getPopularRoomReport() throws SQLException {
        Statement stmt = null;
                
        try {
            ArrayList<HashMap> results = new ArrayList<HashMap>();
            stmt = conn.createStatement();
            String s = String.format("Select mon, Room_category, Location, "
                    + "roomCount from (select MONTH(Start_Date) as mon, "
                    + "Room_category, Location, Count(Room_category) as "
                    + "roomCount from HAS Natural Join RESERVATION "
                    + "Natural Join ROOM \n" +
                    "group by Location, MONTH(Start_Date), Room_category\n" +
                    "order by Location, roomCount DESC) y\n" +
                    "group by Location, mon;");
                

            
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                HashMap hm = new HashMap();
                
                int mon = rs.getInt("mon");
                String cat = rs.getString("Room_category");
                String loc = rs.getString("Location");
                int count = rs.getInt("roomCount");
                
                hm.put("Month", new Integer(mon));
                hm.put("Room_category", cat);
                hm.put("Location", loc);
                hm.put("roomCount", new Integer(count));
                        
                results.add(hm);
                
            }
            
            if (stmt != null) { 
                stmt.close();
            } 
             return results;
            
        } catch (SQLException e) {
            System.err.println("Get Popular Rooms Exception: " + e.getMessage());
            return null;
        } 
    }
    
    public static ArrayList<HashMap> getReservationReport() throws SQLException {
        Statement stmt = null;
                
        try {
            ArrayList<HashMap> results = new ArrayList<HashMap>();
            stmt = conn.createStatement();
            String s = String.format("select MONTH(Start_Date) "
                    + "as mon, Location, "
                    + "Count(ReservationID) as reservationCount "
                    + "from HAS Natural Join RESERVATION \n "
                    + "where not Is_Cancelled\n "
                    + "group by Location, mon\n order by mon;");
                            
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                HashMap hm = new HashMap();
                
                int mon = rs.getInt("mon");
                String loc = rs.getString("Location");
                int count = rs.getInt("reservationCount");
                
                hm.put("Month", new Integer(mon));
                hm.put("Location", loc);
                hm.put("reservationCount", new Integer(count));
                        
                results.add(hm);
                
            }
            
            if (stmt != null) { 
                stmt.close();
            } 
             return results;
            
        } catch (SQLException e) {
            System.err.println("Reservation Report Exception: " + e.getMessage());
            return null;
        } 
    }
    
}
