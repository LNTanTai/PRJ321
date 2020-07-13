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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longlb.registration.RegistrationCreateNewError;
import longlb.registration.RegistrationDAO;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccountErrNotify";
    private final String LOGIN_PAGE = "login";

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
	String confirm = request.getParameter("txtConfirm");
	String fullName = request.getParameter("txtFullname");

	String url = ERROR_PAGE;
	RegistrationCreateNewError errors = new RegistrationCreateNewError();
	boolean foundErr = false;

	try {
	    //1. Check all user errors
	    if (username.trim().length() < 6 || username.trim().length() > 20) {
		foundErr = true;
		errors.setUsernameError("Username requires input value length from 6 to 20 chars");
	    }
	    if (password.trim().length() < 6 || password.trim().length() > 30) {
		foundErr = true;
		errors.setPasswordError("Password requires input value length from 6 to 30 chars");
	    } else if (!confirm.trim().equals(password.trim())) {
		foundErr = true;
		errors.setConfirmNotMatched("Confirm must match password");
	    }
	    if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
		foundErr = true;
		errors.setFullNameLengthError("Full name requires input value length from 2 to 50 chars");
	    }

	    if (foundErr) {
		request.setAttribute("CREATE_ERRORS", errors);
	    } else {
		//2. Call DAO
		RegistrationDAO dao = new RegistrationDAO();
		boolean result = dao.createAccount(username, password, fullName, false);
		if (result) {
		    url = LOGIN_PAGE;
		}//end if result
	    }
	} catch (SQLException ex) {
	    String msg = ex.getMessage();
	    log("CreateNewAccountServlet _ SQLException " + msg);
	    if (msg.contains("duplicate")) {
		errors.setUsernameIsExisted(username + " is EXISTED!!!");
		request.setAttribute("CREATE_ERRORS", errors);
	    }//end if msg
	} catch (NamingException ex) {
	    log("CreateNewAccountServlet _ NamingException " + ex.getMessage());
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
