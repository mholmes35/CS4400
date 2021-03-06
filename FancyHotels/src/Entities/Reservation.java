/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.sql.Date;

/**
 *
 * @author morganholmes
 */
public class Reservation {
    private int reservationID;
    private Date startDate; //Type?
    private Date endDate;
    private boolean isCancelled;
    private float totalCost;
    private String p_CardNo;
    private String cust_username;

    
    
    public Reservation(Date startDate, Date endDate, boolean isCancelled, 
            float totalCost, String p_CardNo, String cust_username){
        
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCancelled = isCancelled;
        this.totalCost = totalCost;
        this.p_CardNo = p_CardNo;
        this.cust_username = cust_username;
        //check the dates
        //probably need to plt the string at the '/' and compare each element
        //is it in the past? throw error
        //is it the end before the begining? throw error
        
        
        //GENERATE THE ID (max id +1 ?)
        //make new Reserved Room
        //put it in the database
        
        
    }
    /**
     * @return the reservationID
     */
    public int getReservationID() {
        return reservationID;
    }

    /**
     * @param reservationID the reservationID to set
     */
    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the isCancelled
     */
    public boolean isIsCancelled() {
        return isCancelled;
    }

    /**
     * @param isCancelled the isCancelled to set
     */
    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    /**
     * @return the totalCost
     */
    public float getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return the p_CardNo
     */
    public String getP_CardNo() {
        return p_CardNo;
    }

    /**
     * @param p_CardNo the p_CardNo to set
     */
    public void setP_CardNo(String p_CardNo) {
        this.p_CardNo = p_CardNo;
    }

    /**
     * @return the cust_username
     */
    public String getCust_username() {
        return cust_username;
    }

    /**
     * @param cust_username the cust_username to set
     */
    public void setCust_username(String cust_username) {
        this.cust_username = cust_username;
    }
}
