/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author natton
 */
public class SessionListenerr implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        System.out.println("Session created: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
//        System.out.println("Session destroyed: " + se.getSession().getId());
    }
}
