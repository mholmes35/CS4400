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
public class Reservation {
    private int reservationID;
    private String startDate; //Type?
    private String endDate;
    private boolean isCancelled;
    private float totalCost;
    private int p_CardNo;
    private String cust_username;

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
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
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
    public int getP_CardNo() {
        return p_CardNo;
    }

    /**
     * @param p_CardNo the p_CardNo to set
     */
    public void setP_CardNo(int p_CardNo) {
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
