<%-- 
    Document   : cart
    Created on : Jun 20, 2020, 1:57:33 PM
    Author     : natton
--%>

<%@page import="tientt.item.ItemDTO"%>
<%@page import="tientt.item.ItemDAO"%>
<%@page import="java.util.Map"%>
<%@page import="tientt.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your cart</title>
    </head>
    <body>
        <% 
        CartObj cart = (CartObj) session.getAttribute("CART");
        if (cart != null){
            Map<String, Integer> items = cart.getItems();
            if (items!=null){
                %> 
                <form action="DispatchController">
                
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                        ItemDAO dao = new ItemDAO();
                        int count = 0;
                        for (String ID : items.keySet()){
                            ItemDTO dto = dao.getBook(ID);
                            if (dto!=null){
                                %> 
                                <tr>
                                    <td><%= ++count %></td>
                                    <td><%= dto.getName() %></td>
                                    <td><%= items.get(ID) %></td>
                                    <td>
                                        <input type="checkbox" name="chkSelectedItem" value="<%= ID %>" />
                                    </td>
                                </tr>
                        <%
                            }//end if dto is not null
                        }//end for items key set
                        %>
                        <tr>
                            <td colspan="3">
                                <a href="DispatchController?action=DisplayShoppingPage">Click here to continue shopping</a>
                            </td>
                            <td>
                                <button type="submit" value="DeletedItem" name="action">Delete Selected Item</button>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <a href="DispatchController?action=CheckOut">Check out</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                </form>
        <%
                return;
            }//end if items is not null
        }//end if cart is existed
        %>
        <h2>No items in your cart</h2>
        <a href="DispatchController?action=DisplayShoppingPage">Click here to continue shopping</a>
    </body>
</html>
