/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.registration;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class RegistrationUpdateError implements Serializable{
    private String passwordIsEmpty;

    /**
     * @return the passwordIsEmpty
     */
    public String getPasswordIsEmpty() {
	return passwordIsEmpty;
    }

    /**
     * @param passwordIsEmpty the passwordIsEmpty to set
     */
    public void setPasswordIsEmpty(String passwordIsEmpty) {
	this.passwordIsEmpty = passwordIsEmpty;
    }
    
    
}
