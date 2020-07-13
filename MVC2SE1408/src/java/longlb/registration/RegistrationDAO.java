/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.registration;

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
 * @author Long Le
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
	    throws SQLException, NamingException {
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;

	try {
	    //1. Make connection to DB
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		//2. Create SQL string
		String sql = "SELECT username, password, lastname, isAdmin "
			+ "FROM Registration "
			+ "WHERE username=? AND password=?";

		//3. Create Statement
		stm = con.prepareStatement(sql);
		stm.setString(1, username);
		stm.setString(2, password);

		//4. Query Database
		rs = stm.executeQuery();

		//5. Process Data
		if (rs.next()) {
		    String fullName = rs.getString("lastname");
		    boolean role = rs.getBoolean("isAdmin");
		    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
		    return dto;
		} //end if rs is not null
	    } //end of con is not null
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

    private List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
	return listAccounts;
    }

    public void searchLastname(String searchValue)
	    throws SQLException, NamingException {
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;

	try {
	    //1. Make connection to DB
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		//2. Create SQL string
		String sql = "SELECT username,password,lastname,isAdmin "
			+ "FROM Registration "
			+ "WHERE lastname LIKE ?";

		//3. Create statement
		stm = con.prepareStatement(sql);
		stm.setString(1, "%" + searchValue + "%");

		//4. Query data
		rs = stm.executeQuery();

		//5.process data
		while (rs.next()) {
		    String username = rs.getString("username");
		    String password = rs.getString("password");
		    String fullName = rs.getString("lastname");
		    boolean role = rs.getBoolean("isAdmin");

		    RegistrationDTO dto = new RegistrationDTO(username, password,
			    fullName, role);

		    if (this.listAccounts == null) {
			this.listAccounts = new ArrayList<>();
		    } //end if list account is not null
		    this.listAccounts.add(dto);
		} //end while rs is not null
	    } //end if con is not null
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

    public boolean deleteAccount(String username) 
	    throws SQLException, NamingException {
	Connection con = null;
	PreparedStatement stm = null;

	try {
	    //1. Make connection to DB
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		//2. Create SQL string
		String sql = "DELETE FROM Registration "
			+ "WHERE username=?";

		//3. Create Statement
		stm = con.prepareStatement(sql);
		stm.setString(1, username);

		//4. Query Database
		int row = stm.executeUpdate();

		//5.Process data
		if (row > 0) {
		    return true;
		}//end of row is positive

	    } //end of con is not null
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
    
    public boolean updateAccount (String username, String password, boolean role)
	    throws SQLException, NamingException{
	Connection con = null;
	PreparedStatement stm = null;
	
	try {
	    //1. Make connection
	    con = DBHelpers.makeConnection();
	    
	    if (con != null){
		//2. Create SQL String
		String sql = "UPDATE Registration "
			+ "SET password=?, isAdmin=? "
			+ "WHERE username=?";
		
		//3. Cretae statement
		stm = con.prepareStatement(sql);
		stm.setString(1, password);
		stm.setBoolean(2, role);
		stm.setString(3, username);
		
		//4. Query Database
		int row = stm.executeUpdate();
		
		//5. Process data
		if (row > 0){
		    return true;
		}
	    }//end if con is not null
	} finally{
	    if (stm != null){
		stm.close();
	    }
	    if (con != null){
		con.close();
	    }
	}
	return false;
    }
    
    public boolean createAccount(String username, String password, 
	    String fullName, boolean role) 
	    throws SQLException, NamingException{
	Connection con = null;
	PreparedStatement stm = null;
	
	try {
	    //1. Make connection
	    con = DBHelpers.makeConnection();
	    
	    if (con != null){
		//2. Create SQL string
		String sql = "INSERT INTO Registration(username, password, lastname, isAdmin) "
			+ "VALUES (?, ?, ?, ?)";
		
		//3. Create Query statement
		stm = con.prepareStatement(sql);
		stm.setString(1, username);
		stm.setString(2, password);
		stm.setString(3, fullName);
		stm.setBoolean(4, role);
		
		//4. Query Database
		int row = stm.executeUpdate();
		
		//5. Process data
		if (row > 0){
		    return true;
		}
	    }//end if con is not null
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
