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
public class PaymentInformation {
    private String pName;
    private String expDate;
    private int cvv;
    private int cardNum;
    private String c_Username;

    /**
     * @return the pName
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName the pName to set
     */
    public void setpName(String pName) {
        this.pName = pName;
    }

    /**
     * @return the expDate
     */
    public String getExpDate() {
        return expDate;
    }

    /**
     * @param expDate the expDate to set
     */
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    /**
     * @return the cvv
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the cardNum
     */
    public int getCardNum() {
        return cardNum;
    }

    /**
     * @param cardNum the cardNum to set
     */
    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * @return the c_Username
     */
    public String getC_Username() {
        return c_Username;
    }

    /**
     * @param c_Username the c_Username to set
     */
    public void setC_Username(String c_Username) {
        this.c_Username = c_Username;
    }
    
}
