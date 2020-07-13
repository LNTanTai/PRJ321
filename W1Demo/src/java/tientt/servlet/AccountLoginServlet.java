/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tientt.tbl_User.Tbl_UserDAO;

/**
 *
 * @author natton
 */
public class AccountLoginServlet extends HttpServlet {

    private final String ACCOUNT_INVALID_PAGE = "AccountInvalid.html";
    private final String MOBILE_SEARCH_STAFF_SERVLET = "ControllerDispatch?action=SearchStaff";
    private final String MOBILE_SEARCH_USER_SERVLET = "ControllerDispatch?action=SearchUser";

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ACCOUNT_INVALID_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            Tbl_UserDAO dao = new Tbl_UserDAO();
            System.out.println(username+password);
            boolean result = dao.checkLogin(username, password);
            if (result) {
                HttpSession session = request.getSession(true);
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(5 * 60);
                response.addCookie(cookie);
                session.setAttribute("USERNAME", username);
                int role = dao.getRole(username);
                if (role == 1 || role == 2) {
                    url = MOBILE_SEARCH_STAFF_SERVLET;
                } else if (role == 0) {
                    url = MOBILE_SEARCH_USER_SERVLET;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
