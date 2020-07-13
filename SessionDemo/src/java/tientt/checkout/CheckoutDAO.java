/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.checkout;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tientt.utils.DBHelpers;

/**
 *
 * @author natton
 */
public class CheckoutDAO implements Serializable {

    public boolean addCheckout(String username) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO checkout(username) VALUES (?)";
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

    public int getLastID() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = -1;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT MAX(id) as ID FROM checkout";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("ID");
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
        return result;
    }
}
