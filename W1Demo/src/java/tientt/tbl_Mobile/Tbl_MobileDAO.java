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
import java.util.Map;
import javax.naming.NamingException;
import tientt.utils.DBHelpers;

/**
 *
 * @author natton
 */
public class Tbl_MobileDAO implements Serializable {

    private List<Tbl_MobileDTO> listMobile;

    public List<Tbl_MobileDTO> getListMobile() {
        return listMobile;
    }

    public void searchByNameOrID(String searchValue)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearOfProduction,"
                        + " quantity, notSale FROM tbl_Mobile "
                        + "WHERE mobileId LIKE ? OR mobileName LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String desciption = rs.getString("description");
                    double price = rs.getDouble("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    Tbl_MobileDTO dto = new Tbl_MobileDTO(mobileId, desciption, price, mobileName, yearOfProduction, quantity, notSale);

                    if (this.listMobile == null) {
                        this.listMobile = new ArrayList<>();
                    }
                    this.listMobile.add(dto);
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

    public boolean delete(String mobileId) throws
            SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM tbl_Mobile WHERE mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, mobileId);
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

    public void searchByPriceRange(int minPrice, int maxPrice)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearOfProduction,"
                        + " quantity, notSale FROM tbl_Mobile "
                        + "WHERE price >= ? AND price <= ?";
                stm = con.prepareStatement(sql);
                stm.setDouble(1, minPrice);
                stm.setDouble(2, maxPrice);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String desciption = rs.getString("description");
                    double price = rs.getDouble("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    Tbl_MobileDTO dto = new Tbl_MobileDTO(mobileId, desciption, price, mobileName, yearOfProduction, quantity, notSale);

                    if (this.listMobile == null) {
                        this.listMobile = new ArrayList<>();
                    }
                    this.listMobile.add(dto);
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

    private Tbl_MobileDTO getMoblie(String ID) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Tbl_MobileDTO dto = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearOfProduction,"
                        + " quantity, notSale FROM tbl_Mobile "
                        + "WHERE mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String desciption = rs.getString("description");
                    double price = rs.getDouble("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    dto = new Tbl_MobileDTO(mobileId, desciption, price, mobileName, yearOfProduction, quantity, notSale);
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

    public void makeListFromMap(Map<String, Integer> map) throws SQLException, ClassNotFoundException, NamingException {
        for (String ID : map.keySet()) {
            if (this.listMobile == null) {
                this.listMobile = new ArrayList<>();
            }
            Tbl_MobileDTO dto = this.getMoblie(ID);
            this.listMobile.add(dto);
        }
    }

    public boolean update(String ID, String description, double price, int quantity, boolean notSale)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_Mobile SET description = ?, price = ?, quantity = ?, notSale = ? "
                        + "WHERE mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, description);
                stm.setDouble(2, price);
                stm.setInt(3, quantity);
                stm.setBoolean(4, notSale);
                stm.setString(5, ID);
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
