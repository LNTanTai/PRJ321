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

/**
 *
 * @author natton
 */
public class DBHelpers implements Serializable {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //1.load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2.create ConnString
        String url = "jdbc:sqlserver://localhost:1433;databaseName=login";
        //3.open connection
        Connection con = DriverManager.getConnection(url, "sa", "@rretiumm@rvel1011");
        return con;
    }
}
