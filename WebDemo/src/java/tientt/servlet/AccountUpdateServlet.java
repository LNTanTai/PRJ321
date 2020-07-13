/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tientt.login.LoginDAO;
import tientt.login.LoginUpdateError;

/**
 *
 * @author natton
 */
public class AccountUpdateServlet extends HttpServlet {

    private Map<String, String> siteMap;
//    final private String ERROR_PAGE = "error.html";

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
        //get site map
        siteMap = (Map< String, String>) request.getServletContext().getAttribute("SITE_MAP");
        //get request parameter
        String username = request.getParameter("txtHiddenUsername");
        String password = request.getParameter("txtPassword");
        String roleString = request.getParameter("chkAdmin");
        String seachValue = request.getParameter("lastSearchValue");
        boolean role = roleString != null;
        //because we use RequestDispatcher
        //so url need to be retrived directly from site map
        String url = siteMap.get("searchAction") + "?txtSearch=" + seachValue;
        //create error bean
        LoginUpdateError errors = new LoginUpdateError();
        boolean isError = false;
        try {
            if (password.trim().length() <= 0) {
                errors.setPasswordIsNullError("Password cannot be blank");
                isError = true;
            }//end if password is checked
            if (isError == true) {
                request.setAttribute("ERRORS", errors);
                //ERROR_PK is used to identify which updated row contains error
                request.setAttribute("ERROR_PK", username);
            } else {
                LoginDAO dao = new LoginDAO();
                boolean result = dao.updateAccount(username, password, role);
                if (result == false) {
                    url = siteMap.get("error");
                }
            }//end else isError = false
        } catch (ClassNotFoundException ex) {
            log("AccountUpdateServlet _ ClassNotFound _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("AccountUpdateServlet _ SQL _ " + ex.getMessage());
        } catch (NamingException ex) {
            log("AccountUpdateServlet _ Naming _ " + ex.getMessage());
        } finally {
            //if error happens we want to forward the errors obj to search.jsp
            //to avoid duplicated parameters, we need to name request parameters distinctly
            //such as txtSearch vs txtHiddenUsername
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

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
