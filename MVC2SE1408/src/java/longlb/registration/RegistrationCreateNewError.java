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
public class RegistrationCreateNewError implements Serializable{
    private String usernameError;
    private String passwordError;
    private String confirmNotMatched;
    private String fullNameLengthError;
    private String usernameIsExisted;

    /**
     * @return the usernameError
     */
    public String getUsernameError() {
	return usernameError;
    }

    /**
     * @param usernameError the usernameError to set
     */
    public void setUsernameError(String usernameError) {
	this.usernameError = usernameError;
    }

    /**
     * @return the passwordError
     */
    public String getPasswordError() {
	return passwordError;
    }

    /**
     * @param passwordError the passwordError to set
     */
    public void setPasswordError(String passwordError) {
	this.passwordError = passwordError;
    }

    /**
     * @return the confirmNotMatched
     */
    public String getConfirmNotMatched() {
	return confirmNotMatched;
    }

    /**
     * @param confirmNotMatched the confirmNotMatched to set
     */
    public void setConfirmNotMatched(String confirmNotMatched) {
	this.confirmNotMatched = confirmNotMatched;
    }

    /**
     * @return the fullNameLengthError
     */
    public String getFullNameLengthError() {
	return fullNameLengthError;
    }

    /**
     * @param fullNameLengthError the fullNameLengthError to set
     */
    public void setFullNameLengthError(String fullNameLengthError) {
	this.fullNameLengthError = fullNameLengthError;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
	return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
	this.usernameIsExisted = usernameIsExisted;
    }
    
    
    
}
