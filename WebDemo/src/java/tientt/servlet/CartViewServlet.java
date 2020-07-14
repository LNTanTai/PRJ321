/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tientt.cart.CartObject;
import tientt.item.ItemDAO;
import tientt.item.ItemDTO;

/**
 *
 * @author natton
 */
public class CartViewServlet extends HttpServlet {

//    private final String VIEW_CART_PAGE = "cart.jsp";
    Map<String, String> siteMap;

    private Map<ItemDTO, Integer> mappingIDStringToDTO(Map<String, Integer> items) {
        Map<ItemDTO, Integer> mapDTO = new HashMap<>();
        try {
            ItemDAO dao = new ItemDAO();
            for (String ID : items.keySet()) {
                ItemDTO dto = dao.findItemByID(ID);
                if (dto != null) {
                    mapDTO.put(dto, items.get(ID));
                }//end if dto is not null
            }//end for items keySet
        } catch (SQLException ex) {
            log("CartViewServlert _ SQL _ " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("CartViewServlert _ ClassNotFound _ " + ex.getMessage());
        } catch (NamingException ex) {
            log("CartViewServlert _ Naming _ " + ex.getMessage());
        }
        return mapDTO;
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
        PrintWriter out = response.getWriter();
        //get site map
        siteMap = (Map<String, String>) request.getServletContext().getAttribute("SITE_MAP");
        String url = siteMap.get("cartPage");
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //mapping item id to itemDTO object
                        Map<ItemDTO, Integer> mapDTO = mappingIDStringToDTO(items);
                        request.setAttribute("MAP_DTO", mapDTO);
                    }//end if items is not null
                }//end if cart is not null
            }//end if session is not null
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
