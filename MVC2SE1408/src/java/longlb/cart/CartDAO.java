/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import longlb.util.DBHelpers;

/**
 *
 * @author Long Le
 */
public class CartDAO {
    public boolean writeCartToDatabase(CartObject cart) 
	    throws SQLException, NamingException{
	Connection con = null;
	PreparedStatement stm = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    if (con != null){
		String sql = "INSERT INTO Cart (cartId,productName,quantity)"
			    + "VALUES(?,?,?)";
		stm = con.prepareStatement(sql);
		
		int row = 0;	
		
		Map<String, Integer> itemsList = cart.getItems(); //get cart items list
		String cartId = cart.getCartId(); //get id of car
		
		for (String title : itemsList.keySet()) {
		    stm.setString(1, cartId);
		    stm.setString(2, title);
		    stm.setInt(3, itemsList.get(title));
		    row += stm.executeUpdate();
		} //end for title
		
		if (row == itemsList.size()){
		    return true;
		} //end if row equal productList size
	    } //end if con is null
	} finally {
	    if (stm != null){
		stm.close();
	    }
	    if (con != null){
		con.close();
	    }
	}
	return false;
    }
}
