/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longlb.util.DBHelpers;

/**
 *
 * @author longlb88
 */
public class ProductDAO implements Serializable{
    private List<String> listProduct;

    public List<String> getListProduct() 
	    throws SQLException, NamingException {
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    
	    if (con != null){
		String sql = "SELECT productName "
			+ "FROM Product";
		
		stm = con.prepareStatement(sql);
		rs = stm.executeQuery();
		
		while (rs.next()){
		    if (listProduct == null){
			listProduct = new ArrayList<>();
		    }//end if listProduct is null
		    
		    String productName = rs.getString("productName");
		    listProduct.add(productName);
		} //end if while rs is not empty
	    }//end if con is not null
	} finally{
	    if (rs != null){
		rs.close();
	    }
	    if (stm != null){
		stm.close();
	    }
	    if (con != null){
		con.close();
	    }
	}
	return listProduct;
    }
}
