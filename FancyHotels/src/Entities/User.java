/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author morganholmes
 */
public class User {
    private String username;
    private String password;
    public User(String un, String pw) {
        this.username = un;
        this.password = pw;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean login(String userName, char[] password) {
        
        //query the database
        //is this a valid user? return true 
        
        return true;
    }
}
