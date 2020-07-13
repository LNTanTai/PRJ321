/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tientt.login.LoginCreateNewError;
import tientt.login.LoginDAO;

/**
 *
 * @author natton
 */
public class AuthRegisterServlet extends HttpServlet {

//    private final String ERROR_PAGE = "register.jsp";
//    private final String LOGIN_PAGE = "login";
    Map<String, String> siteMap;

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
        //get site map
        siteMap = (Map<String, String>) request.getServletContext().getAttribute("SITE_MAP");
        //get request parameter
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String classname = request.getParameter("txtClassName");
        //because we use RequestDispatcher
        //so url need to be retrived directly from site map
        String url = siteMap.get("registerPage");
        LoginCreateNewError errors = new LoginCreateNewError();
        boolean foundErr = false;
        try {
            //1. Check all user errors
            if (username.trim().length() <= 0) {
                foundErr = true;
                errors.setUsernameLengthError("Username require not null");
            }
            if (password.trim().length() < 5) {
                foundErr = true;
                errors.setPasswordLengthError("Password require length >= 5");
            } else if (!confirm.equals(password)) {
                foundErr = true;
                errors.setConfirmNotMatchError("Confirm not match");
            }
            if (classname.trim().length() <= 0) {
                foundErr = true;
                errors.setClassnameLengthError("Classname require not null");
            }
            if (foundErr == true) {
                request.setAttribute("ERROR", errors);
            } else {
                LoginDAO dao = new LoginDAO();
                boolean result = dao.addAccount(username, password, classname, false);
                if (result == true) {
                    url = siteMap.get("login");
                } else {
                    url = siteMap.get("error");
                }
            }
        } catch (NamingException e) {
            log("RegisterServlet _ Naming " + e.getMessage());
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("RegisterServlet _ SQL " + msg);
            if (msg.contains("duplicate")) {//check in log file to find keyword
                foundErr = true;
                errors.setUsernameIsExisted(username + " is EXISTED!!!");
                request.setAttribute("ERROR", errors);
            }//end if msg contains duplicate
        } catch (ClassNotFoundException ex) {
            log("RegisterServlet _ ClassNotFound " + ex.getMessage());
        } finally {
            if (foundErr) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
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
