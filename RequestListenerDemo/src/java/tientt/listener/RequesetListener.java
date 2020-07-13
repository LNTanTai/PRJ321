/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Web application lifecycle listener.
 *
 * @author natton
 */
public class RequesetListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request destroyed");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        String num = request.getParameter("txtNumber");
        Boolean result = true;
        try {
            int n = Integer.parseInt(num);
        } catch (NumberFormatException ex) {
            result = false;
        }
        request.setAttribute("VALID", result);
    }
}
