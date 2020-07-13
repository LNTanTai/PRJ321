/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.checkout;

import java.io.Serializable;

/**
 *
 * @author natton
 */
public class CheckoutError implements Serializable {

    private String nameIsNullError;
    private String addressIsNullError;

    public String getNameIsNullError() {
        return nameIsNullError;
    }

    public void setNameIsNullError(String nameIsNullError) {
        this.nameIsNullError = nameIsNullError;
    }

    public String getAddressIsNullError() {
        return addressIsNullError;
    }

    public void setAddressIsNullError(String addressIsNullError) {
        this.addressIsNullError = addressIsNullError;
    }
}
