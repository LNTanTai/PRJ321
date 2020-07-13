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
public class LoginDTO implements Serializable {

    private String username;
    private String password;
    private String classname;
    private boolean role;

    public LoginDTO() {
    }

    public LoginDTO(String username, String password, String classname, boolean role) {
        this.username = username;
        this.password = password;
        this.classname = classname;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

}
