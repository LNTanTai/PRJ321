/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.listener;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Long Le
 */
public class MyServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
	System.out.println("Deploy............");
	ServletContext context = sce.getServletContext();
	System.out.println(context.getRealPath("/"));
	Map<String, String> listMap = new HashMap<>();
	listMap.put("", "StartupServlet");
	listMap.put("loginPage", "login.html");
	listMap.put("login", "LoginServlet");
	listMap.put("logout", "LogoutServlet");
	listMap.put("search", "SearchLastnameServlet");
	listMap.put("searchPage", "search.jsp");
	listMap.put("createAccount", "createNewAccount.html");
	listMap.put("createAccountErrNotify", "createNewAccount.jsp");
	listMap.put("shopping", "DisplayShoppingServlet");
	listMap.put("shoppingPage", "onlineStore.jsp");
	listMap.put("viewCart", "viewCart.jsp");
	listMap.put("invalid", "invalid.html");
	
	context.setAttribute("LISTMAP", listMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
