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

    private final String ACCOUNT_LOGIN_PAGE = "AccountLogin.html";
    private final String ACCOUNT_LOGIN_SERVLET = "AccountLoginServlet";
    private final String STAFF_SEARCH_VIEW_SERVLET = "StaffSearchViewServlet";
    private final String CLIENT_SEARCH_VIEW_SERVLET = "ClientSearchViewServlet";
    private final String ACCOUNT_LOGOUT_SERVLET = "AccountLogoutServlet";
    private final String ACCOUNT_STARTUP_SERVLET = "AccountStartupServlet";
    private final String MOBILE_DELETE_SERVLET = "MobileDeleteServlet";
    private final String MOBILE_UPDATE_SERVLET = "MobileUpdateServlet";
    private final String SHOPPING_CART_ADD_SERVLET = "ShoppingCartAddServlet";
    

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
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        String url = ACCOUNT_LOGIN_PAGE;
        try {
            if (action == null) {
                url = ACCOUNT_STARTUP_SERVLET;
            } else if (action.equals("Login")) {
                url = ACCOUNT_LOGIN_SERVLET;
            } else if (session != null) {
                if (action.equals("StaffSearch")) {
                    url = STAFF_SEARCH_VIEW_SERVLET;
                } else if (action.equals("ClientSearch")) {
                    url = CLIENT_SEARCH_VIEW_SERVLET;
                } else if (action.equals("Logout")){
                    url = ACCOUNT_LOGOUT_SERVLET;
                } else if (action.equals("DeleteMobile")){
                    url = MOBILE_DELETE_SERVLET;
                } else if (action.equals("UpdateMobile")){
                    url = MOBILE_UPDATE_SERVLET;
                } else if (action.equals("AddToCart")){
                    url = SHOPPING_CART_ADD_SERVLET;
                }
            }
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
