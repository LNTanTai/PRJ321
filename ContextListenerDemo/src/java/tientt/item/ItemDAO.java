/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natton
 */
public class ItemDAO {

    private List<ItemDTO> bookList;

    public List<ItemDTO> getBookList() {
        return bookList;
    }

    public void getBooks(Connection con) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            if (con != null) {
                String url = "SELECT ID, name, price FROM item";
                stm = con.prepareStatement(url);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    ItemDTO dto = new ItemDTO(ID, name, price);

                    if (bookList == null) {
                        bookList = new ArrayList<>();
                    }
                    bookList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
    }
}
