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
import java.sql.Statement;

/**
 *
 * @author morganholmes
 */
public class FancyHotels extends Application {
    FancyHotelSingleton singleton;
    
    @Override
    public void start(Stage primaryStage) {
        
        singleton = FancyHotelSingleton.getInstance();
        
        try {
            boolean connection_status = singleton.connectToDB();
            if (connection_status) {
                Login newLogin = new Login();
                newLogin.setVisible(true);
            } else {
                System.out.println("Unable to connect to database");
            }
            
        } catch(Exception e) {
            System.out.println("Exception while trying to connect to database: " + e);
        }
        
        
        
        
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
        launch(args);
        
    }
}
