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
public class ReservedRoom {
    private int reservedRoomID;
    private int rroomNum;
    private String location;

    /**
     * @return the reservedRoomID
     */
    public int getReservedRoomID() {
        return reservedRoomID;
    }

    /**
     * @param reservedRoomID the reservedRoomID to set
     */
    public void setReservedRoomID(int reservedRoomID) {
        this.reservedRoomID = reservedRoomID;
    }

    /**
     * @return the rroomNum
     */
    public int getRroomNum() {
        return rroomNum;
    }

    /**
     * @param rroomNum the rroomNum to set
     */
    public void setRroomNum(int rroomNum) {
        this.rroomNum = rroomNum;
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
