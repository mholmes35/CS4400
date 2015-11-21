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
public class Room {
    private int numPeople;
    private String roomCategory; //is this worth making an enumerated type?
    private float costPerDay;
    private int roomNumber;
    private String location; //?
    private boolean extraBed;
    private float extraBedCost;
    
    
    /**
     * @return the numPeople
     */
    public int getNumPeople() {
        return numPeople;
    }

    /**
     * @param numPeople the numPeople to set
     */
    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    /**
     * @return the roomCategory
     */
    public String getRoomCategory() {
        return roomCategory;
    }

    /**
     * @param roomCategory the roomCategory to set
     */
    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    /**
     * @return the costPerDay
     */
    public float getCostPerDay() {
        return costPerDay;
    }

    /**
     * @param costPerDay the costPerDay to set
     */
    public void setCostPerDay(float costPerDay) {
        this.costPerDay = costPerDay;
    }

    /**
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    /**
     * @return the extraBed
     */
    public boolean isExtraBed() {
        return extraBed;
    }

    /**
     * @param extraBed the extraBed to set
     */
    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

    /**
     * @return the extraBedCost
     */
    public float getExtraBedCost() {
        return extraBedCost;
    }

    /**
     * @param extraBedCost the extraBedCost to set
     */
    public void setExtraBedCost(float extraBedCost) {
        this.extraBedCost = extraBedCost;
    }
    
}
