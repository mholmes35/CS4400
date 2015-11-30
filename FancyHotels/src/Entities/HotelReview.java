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
public class HotelReview {
    private String comment;
    private String rating;
    private String location; //should this be something else?
    private int reviewNum;
    private String cust_UN;
    
    public HotelReview(String un, String comment, String rating, String location, int reviewNum) {
        
        this.cust_UN = un;
        this.comment = comment;
        this.rating = rating;
        this.location = location;
        this.reviewNum = reviewNum;
        
    }
        
    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
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
     * @return the reviewNum
     */
    public int getReviewNum() {
        return reviewNum;
    }

    /**
     * @param reviewNum the reviewNum to set
     */
    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    /**
     * @return the cust_UN
     */
    public String getCust_UN() {
        return cust_UN;
    }

    /**
     * @param cust_UN the cust_UN to set
     */
    public void setCust_UN(String cust_UN) {
        this.cust_UN = cust_UN;
    }
}
