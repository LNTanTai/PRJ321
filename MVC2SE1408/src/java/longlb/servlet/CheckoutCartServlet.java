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
import javax.servlet.http.HttpSession;
import longlb.cart.CartObject;
import longlb.cart.CartDAO;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "CheckoutCartServlet", urlPatterns = {"/CheckoutCartServlet"})
public class CheckoutCartServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String SHOPPING_PAGE = "onlineStore.jsp";

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
	String url = ERROR_PAGE;

	try {
	    //1. Customer goes to cart place
	    HttpSession session = request.getSession(false);
	    if (session != null) {
		//2. Customer takes his/her cart
		CartObject cart = (CartObject) session.getAttribute("CART");
		if (cart != null) {
		    cart.generateID(); //generate id at checkout time
		    CartDAO dao = new CartDAO();
		    boolean result = dao.writeCartToDatabase(cart);

		    if (result) {
			cart = null;
			session.setAttribute("CART", cart);
			url = SHOPPING_PAGE;
		    } //end if result is true
		} //end if cart is existed
	    } //end if session is existed
	} catch (SQLException ex) {
	    log("CheckoutCartServlet _ SQLException " + ex.getMessage());
	} catch (NamingException ex) {
	    log("CheckoutCartServlet _ NamingException " + ex.getMessage());

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
