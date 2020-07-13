/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.utils;

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

    public static Connection makeConnection() throws NamingException, SQLException, ClassNotFoundException {
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("W1DemoDB");
        Connection con = ds.getConnection();
        return con;

//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=demoW1";
//        Connection con = DriverManager.getConnection(url, "sa", "@rretiumm@rvel1011");
//        return con;
    }

}
