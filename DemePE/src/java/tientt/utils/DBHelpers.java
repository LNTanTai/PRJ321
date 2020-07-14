package tientt.utils;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author natton
 */
public class DBHelpers implements Serializable {

    public static Connection getConnection() throws SQLException, ClassNotFoundException, NamingException {
        Context initContext = new InitialContext();
        Context tomcatContext = (Context) initContext.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("PEDB");
        Connection con = ds.getConnection();
        return con;
    }
}
