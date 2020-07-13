/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.checkoutDetail;

import java.io.Serializable;

/**
 *
 * @author natton
 */
public class CheckoutDetailDTO implements Serializable {

    private int checkoutID;
    private String itemID;
    private int quantity;

    public CheckoutDetailDTO() {
    }

    public CheckoutDetailDTO(int checkoutID, String itemID, int quantity) {
        this.checkoutID = checkoutID;
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public int getCheckoutID() {
        return checkoutID;
    }

    public void setCheckoutID(int checkoutID) {
        this.checkoutID = checkoutID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
