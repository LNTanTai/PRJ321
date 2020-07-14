/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.listener;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author natton
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> siteMap = new HashMap<>();
        siteMap.put("login", "login.html");
        siteMap.put("", "login.html");
        siteMap.put("loginAction", "AuthLoginServlet");
        siteMap.put("invalid", "invalid.html");
        siteMap.put("searchAction", "AccountSearchServlet");
        siteMap.put("search", "search.jsp");
        siteMap.put("logoutAction", "AuthLogoutServlet");
        siteMap.put("deleteAction", "AccountDeleteServlet");
        siteMap.put("error", "error.html");
        siteMap.put("updateAction", "AccountUpdateServlet");

        ServletContext context = sce.getServletContext();
        context.setAttribute("SITE_MAP", siteMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
