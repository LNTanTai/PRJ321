/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.tbl_Mobile;

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
public class Tbl_MobileDAO implements Serializable {

    List<Tbl_MobileDTO> listMobiles;

    public List<Tbl_MobileDTO> getListMobiles() {
        return listMobiles;
    }

    public void searchMobilesByNameOrID(String searchValue) throws SQLException,
            ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearofProduction, "
                        + "quantity, notSale FROM tbl_Mobile WHERE "
                        + "mobileID LIKE ? OR mobileName LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearofProduction = rs.getInt("yearofProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    Tbl_MobileDTO dto = new Tbl_MobileDTO(mobileId, description, price, mobileName, yearofProduction, quantity, notSale);
                    if (this.listMobiles == null) {
                        this.listMobiles = new ArrayList<>();
                    }

                    this.listMobiles.add(dto);
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

    public boolean deleteMobile(String id) throws SQLException, ClassNotFoundException,
            NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "DELETE FROM tbl_Mobile WHERE mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
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

    public boolean updateMobile(String id, String description, int quatity, double price, boolean notSale)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "UPDATE tbl_Mobile SET description = ?, quantity = ?, notSale = ?, "
                        + "price = ? where mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, description);
                stm.setInt(2, quatity);
                stm.setBoolean(3, notSale);
                stm.setDouble(4, price);
                stm.setString(5, id);
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

    public void searchMobileByPriceRange(double minPrice, double maxPrice) throws
            SQLException, ClassNotFoundException, NamingException {
        if (minPrice >= maxPrice) {
            double temp = maxPrice;
            maxPrice = minPrice;
            minPrice = temp;
        }
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearofProduction, "
                        + "quantity, notSale FROM tbl_Mobile WHERE "
                        + "price >= ? AND price <= ?";
                stm = con.prepareStatement(sql);
                stm.setDouble(1, minPrice);
                stm.setDouble(2, maxPrice);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearofProduction = rs.getInt("yearofProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    Tbl_MobileDTO dto = new Tbl_MobileDTO(mobileId, description, price, mobileName, yearofProduction, quantity, notSale);
                    if (this.listMobiles == null) {
                        this.listMobiles = new ArrayList<>();
                    }

                    this.listMobiles.add(dto);
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

}
