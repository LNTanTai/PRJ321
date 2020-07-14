/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tientt.login.LoginDAO;
import tientt.login.LoginDTO;

/**
 *
 * @author natton
 */
public class AccountDeleteServlet extends HttpServlet {

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
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        String url = "actionFailed";
        try {
            LoginDAO dao = new LoginDAO();
            boolean result = dao.deleteAccount(username);
            if (result == true) {//call search servlet again
                HttpSession session = request.getSession(false);
                LoginDTO currentUser = (LoginDTO) session.getAttribute("USER");
                if (username.equals(currentUser.getUsername())) {
                    url = "logoutAction";
                } else {
                    url = "searchAction?txtSearch=" + searchValue;
                }//end else username equal current user's username
            }//end if result is true
        } catch (SQLException e) {
            log("AccountDeleteServlet _ SQL _ " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("AccountDeleteServlet _ ClassNotFound _ " + e.getMessage());
        } catch (NamingException ex) {
            log("AccountDeleteServlet _ NamingException _ " + ex.getMessage());
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
