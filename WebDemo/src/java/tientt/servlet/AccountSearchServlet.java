/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tientt.login.LoginDAO;
import tientt.login.LoginDTO;

/**
 *
 * @author natton
 */
public class AccountSearchServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "search.html";
//    private final String SHOW_SEARCH_PAGE = "ShowSearchResultServlet";
//    private final String SEARCH_PAGE = "search.jsp";
    private Map<String, String> siteMap;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //get site map
        siteMap = (Map< String, String>) request.getServletContext().getAttribute("SITE_MAP");
        //get request parameter
        String searchValue = request.getParameter("txtSearch");
        //because we use RequestDispatcher
        //so url need to be retrived directly from site map
        String url = siteMap.get("search");
        try {
            //need to check null beacause after login we send redirect to searchServlet not search.jsp
            //beacause we need to add header to search.jsp
            //and can't do that without using scriptlet in search.jsp
            if (searchValue != null && searchValue.trim().length() > 0) {
                LoginDAO dao = new LoginDAO();
                dao.searchAccountByClassname(searchValue);
                List<LoginDTO> result = dao.getListAccounts();
                request.setAttribute("SEARCH_RESULT", result);
            }//end if searchValue is not null and not blank
        } catch (SQLException e) {
            log("AccountSearchServlet _ SQL _ " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("AccountSearchServlet _ ClassNotFound _ " + e.getMessage());
        } catch (NamingException e) {
            log("AccountSearchServlet _ Naming _ " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
