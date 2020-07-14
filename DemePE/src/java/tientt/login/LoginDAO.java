/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.login;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tientt.utils.DBHelpers;

/**
 *
 * @author natton
 */
public class LoginDAO implements Serializable {

    public LoginDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        LoginDTO dto = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "SELECT classname, isAdmin FROM login WHERE username = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("classname");
                    boolean isRole = rs.getBoolean("isAdmin");
                    dto = new LoginDTO(username, password, fullname, isRole);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
    private List<LoginDTO> accountList;

    public List<LoginDTO> getAccountList() {
        return accountList;
    }

    public void findAccountByClassnam(String classname) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "SELECT username, password, classname, isAdmin FROM login WHERE classname LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + classname + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("classname");
                    boolean isRole = rs.getBoolean("isAdmin");
                    LoginDTO dto = new LoginDTO(username, password, fullname, isRole);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<LoginDTO>();
                    }
                    this.accountList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "DELETE FROM login WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return false;
    }

    public boolean updateAccount(String username, String password, boolean role) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "UPDATE login SET password = ?, isAdmin = ? WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return false;
    }

}
