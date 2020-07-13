/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ControllerDispatch extends HttpServlet {

    private final String ACCOUNT_LOGIN_SERVLET = "AccountLoginServlet";
    private final String LOGIN_PAGE = "AccountLogin.html";
    private final String MOBILE_SEARCH_STAFF_SERVLET = "MobileSearchStaffServlet";
    private final String MOBILE_SEARCH_USER_SERVLET = "MobileSearchUserServlet";
    private final String CONTROLLER_STARTUP = "ControllerStartup";
    private final String ACCOUNT_LOGOUT_SERVLET = "AccountLogoutServlet";
    private final String MOBILE_DELETE_SERVLET = "MobileDeleteServlet";
    private final String ERROR_PAGE = "Error.html";
    private final String MOBILE_UPDATE_SERVLET = "MobileUpdateServlet";
    private final String SHOPPING_ADD_TO_CART_SERVLET = "ShoppingAddToCartServlet";
    private final String SHOPPING_VIEW_CART_SERVLET = "ShoppingViewCartServlet";
    private final String SHOPPING_CART_DELETE_SERVLET = "ShoppingCartDeleteServlet";
    

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
            String action = request.getParameter("action");
            HttpSession session = request.getSession(false);
//            if (session!=null){
//                System.out.println(session.getId());
//            } else {
//                System.out.println("no session");
//            }
//            System.out.println(action);
            if (action == null) {
                url = CONTROLLER_STARTUP;
            } else if (action.equals("Login")) {
                url = ACCOUNT_LOGIN_SERVLET;
            } else if (session != null) {
                if (action.equals("SearchStaff")) {
                    url = MOBILE_SEARCH_STAFF_SERVLET;
                } else if (action.equals("SearchUser")) {
                    url = MOBILE_SEARCH_USER_SERVLET;
                } else if (action.equals("Logout")) {
                    url = ACCOUNT_LOGOUT_SERVLET;
                } else if (action.equals("Delete")) {
                    url = MOBILE_DELETE_SERVLET;
                } else if (action.equals("Update")){
                    url = MOBILE_UPDATE_SERVLET;
                } else if (action.equals("AddToCart")){
                    url = SHOPPING_ADD_TO_CART_SERVLET;
                } else if(action.equals("View Cart")){
                    url = SHOPPING_VIEW_CART_SERVLET;
                } else if (action.equals("DeleteItemInCart")){
                    url = SHOPPING_CART_DELETE_SERVLET;
                }
            }
//            if (session == null) {
//                if (action == null) {
//                    
//                } else if (action.equals("Login")) {
//                    url = ACCOUNT_LOGIN_SERVLET;
//                }
//            } else {
//                if (action == null){
//                    
//                } else if (action.equals("SearchStaff")){
//                    url = MOBILE_SEARCH_STAFF_SERVLET;
//                } else if (action.equals("SearchUser")){
//                    //url = MOBILE_SEARCH_USER_SERVLET;
//                }
//            }
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
