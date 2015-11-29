/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyhotels;

import Entities.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author morganholmes
 */
public class FancyHotels extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Login newLogin = new Login();
        newLogin.setVisible(true);
        
        //this is where we will keep the user. info probably
        
        
        //Scene scene = new Scene(root, 300, 250);
        
       // primaryStage.setTitle("Fancy Hotels");
        //primaryStage.setScene(scene);
       // primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //final FancyHotels app;
        Connection conn = null;
        boolean connection_status = connectToDB(conn);
        
        launch(args);
        
    }
    
    public static boolean connectToDB(Connection conn) {
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
    
}
