/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.tbl_User;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import tientt.utils.DBHelpers;

/**
 *
 * @author natton
 */
public class Tbl_UserDAO implements Serializable {

    public Tbl_UserDTO checkLogin(String username, int password) throws
            SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Tbl_UserDTO dto = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "SELECT fullName, userRole FROM tbl_User WHERE "
                        + "userId = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    int userRole = rs.getInt("userRole");
                    dto = new Tbl_UserDTO(username, password, fullName, userRole);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
    
}
