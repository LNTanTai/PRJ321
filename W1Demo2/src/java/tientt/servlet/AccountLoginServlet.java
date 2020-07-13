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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tientt.tbl_User.Tbl_UserDAO;
import tientt.tbl_User.Tbl_UserDTO;

/**
 *
 * @author natton
 */
public class AccountLoginServlet extends HttpServlet {

    private final String ACCOUNT_INVALID_PAGE = "AccountInvalid.html";
    private final String CLIENT_SEARCH_PAGE = "ClientSearch.jsp";
    private final String STAFF_SEARCH_PAGE = "StaffSearch.jsp";

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
        String username = request.getParameter("txtUsername");
        String passwordString = request.getParameter("txtPassword");
        String remember = request.getParameter("chkRemember");
        String url = ACCOUNT_INVALID_PAGE;
        try {
            int password = Integer.parseInt(passwordString);
            Tbl_UserDAO dao = new Tbl_UserDAO();
            Tbl_UserDTO dto = dao.checkLogin(username, password);
            if (dto != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("USER_FULLNAME", dto.getFullName());
                session.setAttribute("USER_USERNAME", dto.getUserId());
                if (remember != null) {
                    Cookie cookie = new Cookie(username, passwordString);
                    cookie.setMaxAge(5 * 60);
                    response.addCookie(cookie);
                }
                int userRole = dto.getUserRole();
                if (userRole == 1 || userRole == 2){
                    url =STAFF_SEARCH_PAGE;
                }
                if (userRole == 0){
                    url = CLIENT_SEARCH_PAGE;
                }
            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
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
