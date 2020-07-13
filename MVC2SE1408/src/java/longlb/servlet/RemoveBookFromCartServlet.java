/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longlb.cart.CartObject;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "RemoveBookFromCartServlet", urlPatterns = {"/RemoveBookFromCartServlet"})
public class RemoveBookFromCartServlet extends HttpServlet {

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

	try {
	    //1. Customer goes to cart place
	    HttpSession session = request.getSession(false);
	    if (session != null){
		//2. Customer takes his/her cart
		CartObject cart = (CartObject) session.getAttribute("CART");
		
		if (cart != null){
		    //3. Get selected items
		    String[] selectedItems = request.getParameterValues("chkItem");
		    if (selectedItems != null){
			//4. Remove selected items from cart
			for (String title : selectedItems){
			    cart.removeItemFromCart(title);
			}//end for title
			session.setAttribute("CART", cart);
		    }//end if selected items is existed
		}//end if cart is existed
	    }//end if session is existed
	    
	} finally {
	    //5. call view cart agian
//	    String urlRewriting = "DispatchController"
//				+ "?btAction=View your cart";
	    String url = "viewCart.jsp";
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
