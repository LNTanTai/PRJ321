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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tientt.login.LoginDAO;

/**
 *
 * @author natton
 */
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String SHOPPING_PAGE = "DispatchController?action=DisplayShoppingPage";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String remember = request.getParameter("chkRemember");
        boolean isRemember = remember != null;
//        System.out.println(remember);
        String url = INVALID_PAGE;
        try {
            LoginDAO dao = new LoginDAO();
            boolean result = dao.checkLogin(username, password);
//            System.out.println(username);
//            System.out.println(password);
//            System.out.println(result);
            if (result) {
                url = SHOPPING_PAGE;
                HttpSession session = request.getSession(true);
                session.setAttribute("USERNAME", username);
                session.setAttribute("PASSWORD", password);
                if (isRemember) {
                    Cookie cookie = new Cookie(username, password);
                    cookie.setMaxAge(60 * 5);
                    response.addCookie(cookie);
                }//end if user ask to remember
            }//end if user is valid
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
