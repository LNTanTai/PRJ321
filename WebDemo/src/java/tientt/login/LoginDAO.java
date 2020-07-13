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
import tientt.util.DBHelpers;

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
            //1. connect database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "SELECT classname, isAdmin FROM login WHERE username = ? AND password = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Query Data 
                rs = stm.executeQuery();
                //5.Process Data
                if (rs.next()) {
                    String fullname = rs.getString("classname");
                    boolean role = rs.getBoolean("isAdmin");
                    dto = new LoginDTO(username, password, fullname, role);
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
    private List<LoginDTO> listAccounts;

    public List<LoginDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchAccountByClassname(String searchValue) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "SELECT username, password, classname, isAdmin FROM login WHERE classname LIKE ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.Query Data 
                rs = stm.executeQuery();
                //5.Process Data
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("classname");
                    boolean role = rs.getBoolean("isAdmin");
                    LoginDTO dto = new LoginDTO(username, password, fullname, role);

                    if (this.listAccounts == null) {
                        this.listAccounts = new ArrayList<>();
                    }//end if list account is null
                    this.listAccounts.add(dto);
                }//end while rs not null
            }//end if con is not null
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
            //1. connect database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "DELETE FROM login WHERE username = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Query Data 
                int row = stm.executeUpdate();
                //5.Process Data
                if (row > 0) {
                    return true;
                }
            }//end if con is not null
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

    public boolean updateAccount(String username,
            String password, boolean isAdmin) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "UPDATE login SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }// end if result > 0
            }//end if con is not null
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

    public boolean addAccount(String username, String password, String fullName, boolean isRole) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "INSERT INTO Login(username, password, classname, isAdmin) "
                        + "VALUES(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullName);
                stm.setBoolean(4, isRole);
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }// end if result > 0
            }//end if con is not null
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
