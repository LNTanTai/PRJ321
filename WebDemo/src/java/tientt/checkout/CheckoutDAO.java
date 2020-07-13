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
import javax.naming.NamingException;
import tientt.util.DBHelpers;

/**
 *
 * @author natton
 */
public class CheckoutDAO implements Serializable {

    public String addCheckout(String cusName, String cusAddress) throws
            SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String checkoutID = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "INSERT INTO checkout(cusName, cusAddress) "
                        + "OUTPUT INSERTED.checkoutID VALUES(?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, cusName);
                stm.setString(2, cusAddress);
                rs = stm.executeQuery();
                if (rs.next()) {
                    checkoutID = rs.getString("checkoutID");
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
        return checkoutID;
    }
}
