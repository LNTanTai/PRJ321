/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Long Le
 */
public class DBHelpers implements Serializable{
    public static Connection makeConnection() 
	    throws SQLException, NamingException{
	
	Context currentContext = new InitialContext();
	Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
	
	DataSource ds = (DataSource)tomcatContext.lookup("DS007");
	
	Connection con = ds.getConnection();
	
	return con;
	
//	//1. Load Driver
//	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//	
//	//2. Create Connection String
//	String url = "jdbc:sqlserver://localhost:1433;databaseName=longlb_DemoLogin;instanceName=SQLEXPRESS";
//	
//	//3. Open connection
////	Connection con = DriverManager.getConnection(url, "sa", "Long2ws0808");
//	
//	return con;
    }
}
