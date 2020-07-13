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
import javax.servlet.http.HttpSession;
import tientt.cart.CartObject;
import tientt.checkout.CheckoutDAO;
import tientt.checkout.CheckoutError;
import tientt.checkoutDetail.CheckoutDetailDAO;

/**
 *
 * @author natton
 */
public class CartCheckoutServlet extends HttpServlet {

//    private final String STORE_SERVLET = "DispatchController?btnLogin=displayStore";
    private void checkout(Map<String, String> items){
        
    }
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
        PrintWriter out = response.getWriter();
        //get request parameter
        String cusName = request.getParameter("txtCusName");
        String cusAddress = request.getParameter("txtCusAddress");
        //create error bean
        CheckoutError errors = new CheckoutError();
        boolean isError = false;
        String url = "checkout.jsp";
        try {
            //check client's errors
            if (cusName.trim().length() == 0) {
                errors.setNameIsNullError("Name cannot be blank");
                isError = true;
            }//end if cusName is not null
            if (cusAddress.trim().length() == 0) {
                errors.setNameIsNullError("Address cannot be blank");
                isError = true;
            }//end if cusAddress is not null
            if (isError) {
                request.setAttribute("ERROR", errors);
            } else {
                //1.Cashier goes to cart place
                HttpSession session = request.getSession(false);
                if (session != null) {
                    //2.Cashier take customer's cart
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null) {
                        //3.Cashier gets each item and add to order
                        Map<String, Integer> items = cart.getItems();
                        if (items != null) {
                            CheckoutDAO checkoutDAO = new CheckoutDAO();
                            String checkoutID = checkoutDAO.addCheckout(cusName, cusAddress);
                            CheckoutDetailDAO detailDAO = new CheckoutDetailDAO();
                            for (String ID : items.keySet()) {
                                detailDAO.addCheckoutDetail(checkoutID, ID, items.get(ID));
                            }//end for items.keySet
                            url = "storeView";
                        }//end if items is not null
                        session.removeAttribute("CART");
                    }//end if cart is not null
                }//end if session is not null
            }//end else isError == false
        } catch (SQLException ex) {
            log("CartCheckoutServlet _ SQL _ " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("CartCheckoutServlet _ ClassNotFound _ " + ex.getMessage());
        } catch (NamingException ex) {
            log("CartCheckoutServlet _ Naming _ " + ex.getMessage());
        } finally {
            if (isError) {
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
