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
public class LoginCreateNewError implements Serializable {

    private String usernameLengthError;
    private String passwordLengthError;
    private String confirmNotMatchError;
    private String classnameLengthError;
    private String usernameIsExisted;

    
    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getConfirmNotMatchError() {
        return confirmNotMatchError;
    }

    public void setConfirmNotMatchError(String confirmNotMatchError) {
        this.confirmNotMatchError = confirmNotMatchError;
    }

    public String getClassnameLengthError() {
        return classnameLengthError;
    }

    public void setClassnameLengthError(String classnameLengthError) {
        this.classnameLengthError = classnameLengthError;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

}
