/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.item;

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
public class ItemDAO implements Serializable {

    private List<ItemDTO> itemList;

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    public void loadItems() throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "SELECT ID, name, price FROM item";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    ItemDTO dto = new ItemDTO(ID, name, price);

                    if (itemList == null) {
                        itemList = new ArrayList<>();
                    }// end if itemList is null
                    itemList.add(dto);
                }//end while rs has next
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

    public ItemDTO findItemByID(String ID) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "SELECT name, price FROM item WHERE id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    ItemDTO dto = new ItemDTO(ID, name, price);
                    return dto;
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
        return null;
    }
}
