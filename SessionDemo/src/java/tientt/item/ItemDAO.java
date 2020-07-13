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
import tientt.utils.DBHelpers;

/**
 *
 * @author natton
 */
public class ItemDAO implements Serializable {

    private List<ItemDTO> bookList;

    public List<ItemDTO> getBookList() {
        return bookList;
    }

    public void getBooks() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name, price FROM item";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    ItemDTO dto = new ItemDTO(ID, name, price);
                    if (bookList == null) {
                        bookList = new ArrayList<ItemDTO>();
                    }
                    bookList.add(dto);
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
    }

    public ItemDTO getBook(String ID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT name, price FROM item WHERE ID = ?";
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
