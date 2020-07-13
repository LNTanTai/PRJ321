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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author natton
 */
public class ItemDAO implements Serializable {

    private List<ItemDTO> listItem;

    public List<ItemDTO> getListItem() {
        return listItem;
    }

    public void getItems() throws SQLException, ClassNotFoundException, NamingException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/java");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "SELECT ID, name, price FROM item";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("ID");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                ItemDTO dto = new ItemDTO(ID, name, price);

                if (listItem == null) {
                    listItem = new ArrayList<>();
                }
                listItem.add(dto);
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
