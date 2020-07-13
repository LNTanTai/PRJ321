/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author natton
 */
public class DBHelpers implements Serializable {

    public static Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {
        //Lay context tai may minh vi cac OS co cac file system khac nhau
        //
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");//alias name of tomcat
        DataSource ds = (DataSource) tomcatContext.lookup("LOGINDB");
        Connection con = ds.getConnection();
        return con;
//        //1.Load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2.Create connect string
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=login";
//        //3.open connection
//        Connection con = DriverManager.getConnection(url, "sa", "@rretiumm@rvel1011");
//        
//        return con;
    }
}
