/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import tientt.item.ItemDAO;
import tientt.item.ItemDTO;
import tientt.utils.DBHelpers;

/**
 * Web application lifecycle listener.
 *
 * @author natton
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        DBHelpers db;
        try {
            ServletContext sc = session.getServletContext();
            String url = sc.getInitParameter("DB_URL");
            String password = sc.getInitParameter("DB_PASSWORD");
            String user = sc.getInitParameter("DB_USER");

            db = new DBHelpers(url, user, password);
            session.setAttribute("DB", db);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        DBHelpers db;
        try {
            db = (DBHelpers) session.getAttribute("DB");
            if (db != null) {
                db.clossConnection();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
