/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.checkoutDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import tientt.util.DBHelpers;

/**
 *
 * @author natton
 */
public class CheckoutDetailDAO implements Serializable {

    public boolean addCheckoutDetail(String checkoutID, String itemID, int quantity) throws
            SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "INSERT INTO checkoutDetail(checkoutID, itemID, quantity) "
                        + "VALUES (?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, checkoutID);
                stm.setString(2, itemID);
                stm.setInt(3, quantity);
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
