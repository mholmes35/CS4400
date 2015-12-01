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
public class PaymentInformation {
    private String pName;
    private Date expDate;
    private int cvv;
    private String cardNum;
    private String c_Username;
    
    public PaymentInformation(String pName, Date expDate, int cvv, 
            String cardNum, String c_Username) {
        this.pName = pName;
        this.expDate = expDate;
        this.cvv = cvv;
        this.cardNum = cardNum;
        this.c_Username = c_Username;
    }
    
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
    public Date getExpDate() {
        return expDate;
    }

    /**
     * @param expDate the expDate to set
     */
    public void setExpDate(Date expDate) {
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
    public String getCardNum() {
        return cardNum;
    }

    /**
     * @param cardNum the cardNum to set
     */
    public void setCardNum(String cardNum) {
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
