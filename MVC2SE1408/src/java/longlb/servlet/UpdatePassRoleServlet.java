/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longlb.registration.RegistrationDAO;
import longlb.registration.RegistrationUpdateError;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "UpdatePassRoleServlet", urlPatterns = {"/UpdatePassRoleServlet"})
public class UpdatePassRoleServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String LOGIN_PAGE = "login.html";

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
	String password = request.getParameter("txtPassword");
	String admin = request.getParameter("chkAdmin");
	String searchValue = request.getParameter("lastSearchValue");
	boolean role = false;
	if (admin != null) {
	    role = true;
	}

	String url = ERROR_PAGE;
	RegistrationUpdateError errors = new RegistrationUpdateError();
	boolean foundErr = false;
	try {
	    if (password.trim().length() <= 0) {
		foundErr = true;
		errors.setPasswordIsEmpty("Error for change password of " + username 
			+ ". Password must in range form 6 to 30 chars");
	    }
	    if (foundErr) {
		request.setAttribute("UPDATE_ERRORS", errors);
	    } else {
		RegistrationDAO dao = new RegistrationDAO();
		boolean result = dao.updateAccount(username, password, role);
		if (result) {
		    url = "searchLastname"
			    + "&txtSearch=" + searchValue;
		}
	    }
	} catch (SQLException ex) {
	    log("UpdatePassRole _ SQLException " + ex.getMessage());
	} catch (NamingException ex) {
	    log("UpdatePassRole _ NamingException " + ex.getMessage());
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
