/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author natton
 */

public class DispatchController extends HttpServlet {

    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGIN_PAGE = "login.html";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String SHOPPING_PAGE_SERVLET = "ShoppingPageServlet";
    private final String START_UP_CONTROLLER = "StartupController";
    private final String SHOPPING_ADD_TO_CART_SERVLET = "ShoppingAddToCartServlet";
    private final String SHOPPING_VIEW_CART_SERVLET = "ShoppingViewCartServlet";
    private final String SHOPPING_DELETE_ITEM_IN_CART_SERVLER = "ShoppingDeleteItemInCartServlet";
    private final String SHOPPING_CHECK_OUT_SERVLET = "ShoppingCheckOutServlet";
    private final String SHOPPING_VIEW_CHECK_OUT_SERVLET = "ShoppingViewCheckOutServlet";
    

    private boolean isSessionExisted(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        return session != null;
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
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
//        HttpSession session = request.getSession(false);
//        if (session != null){
//            System.out.println("There is a session: "+session.getId());
//        } else{
//            System.out.println("No session");
//        }
        String url = LOGIN_PAGE;
        if (isSessionExisted(request)) {
            if (action.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (action.equals("DisplayShoppingPage")) {
                url = SHOPPING_PAGE_SERVLET;
            }else if (action.equals("Add to cart")){
                url = SHOPPING_ADD_TO_CART_SERVLET;
            } else if (action.equals("View your cart")){
                url = SHOPPING_VIEW_CART_SERVLET;
            } else if (action.equals("DeletedItem")){
                url = SHOPPING_DELETE_ITEM_IN_CART_SERVLER;
            } else if (action.equals("CheckOut")){
                url = SHOPPING_CHECK_OUT_SERVLET;
            } else if(action.equals("ViewCheckOut")){
                url = SHOPPING_VIEW_CHECK_OUT_SERVLET;
            }
        } else {
            if (action == null) {
                url = START_UP_CONTROLLER;
            } else if (action.equals("Login")) {
                url = LOGIN_SERVLET;
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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
