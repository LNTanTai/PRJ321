/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author natton
 */
public class DBHelpers {

    private final String url;
    private final String user;
    private final String password;
    private Connection con;

    public DBHelpers(String url, String user, String password) throws SQLException, ClassNotFoundException {
        this.url = url;
        this.user = user;
        this.password = password;
        setUpConnection();
    }

    private void setUpConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.con = DriverManager.getConnection(this.url, this.user, this.password);
    }

    public Connection getConnection() {
        return con;
    }

    public void clossConnection() throws SQLException {
        this.con.close();
    }

}
