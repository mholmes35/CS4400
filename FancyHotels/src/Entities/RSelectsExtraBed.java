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
public class RSelectsExtraBed {
    private int reservationID;
    private int resRoomNum;
    private String location;

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
     * @return the resRoomNum
     */
    public int getResRoomNum() {
        return resRoomNum;
    }

    /**
     * @param resRoomNum the resRoomNum to set
     */
    public void setResRoomNum(int resRoomNum) {
        this.resRoomNum = resRoomNum;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
