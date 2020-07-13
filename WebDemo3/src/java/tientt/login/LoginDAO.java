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
import tientt.util.DBHelpers;

/**
 *
 * @author natton
 */
public class LoginDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.connection to database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2.create SQL string
                String sql = "SELECT username FROM login WHERE username = ? "
                        + "AND password = ? ";
                //3.create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.query statement
                rs = stm.executeQuery();
                //5.process result
                if (rs.next()) {
                    return true;
                }
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
        return false;
    }
    private List<LoginDTO> listAccounts = null;

    public List<LoginDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchName(String searchValue) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Connect to database
            conn = DBHelpers.getConnection();
            if (conn != null) {
                //2.Make SQL string
                String sql = "SELECT username, password, classname, isAdmin "
                        + "FROM login WHERE classname LIKE ?";
                //3.Make statement
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.Query statement
                rs = stm.executeQuery();
                //5.Process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String classname = rs.getString("classname");
                    boolean role = rs.getBoolean("isAdmin");
                    LoginDTO dto = new LoginDTO(username, password, classname, role);
                    if (listAccounts == null) {
                        listAccounts = new ArrayList<LoginDTO>();
                    }
                    listAccounts.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean deleteAccount(String username) throws ClassNotFoundException, SQLException {
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
}
