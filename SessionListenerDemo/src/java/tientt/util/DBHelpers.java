/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
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

    public static Connection getConnection() throws SQLException, ClassNotFoundException, NamingException {
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=login";
//        Connection con = DriverManager.getConnection(url, "sa", "@rretiumm@rvel1011");
//        return con;
//    }
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("DB");
        Connection con = ds.getConnection();
        return con;
    }
}
