/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.login;

import java.io.Serializable;

/**
 *
 * @author natton
 */
public class LoginUpdateError implements Serializable {

    private String passwordIsNullError;

    public LoginUpdateError() {
    }

    public String getPasswordIsNullError() {
        return passwordIsNullError;
    }

    public void setPasswordIsNullError(String passwordIsNullError) {
        this.passwordIsNullError = passwordIsNullError;
    }

    
}
