<%-- 
    Document   : shopping
    Created on : Jun 20, 2020, 7:30:58 AM
    Author     : natton
--%>

<%@page import="java.util.List"%>
<%@page import="tientt.item.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <h1>Hello <%= session.getAttribute("USERNAME")%></h1>

        <form action="DispatchController">
            <input type="submit" value="Logout" name="action" />
        </form>
        </br>
        <form action="DispatchController">
            <select name="ddItems">
                <% 
                List<ItemDTO> result = (List<ItemDTO>) request.getAttribute("BOOK_LIST");
                if (result != null){
                    for (ItemDTO dto: result){
                        %> 
                        <option value="<%= dto.getID() %>">
                            <%= dto.getName() %>
                        </option>
                <%
                    }//end for dto
                }//end if list of book existed
                %>
            </select> </br>
            <input type="submit" value="Add to cart" name="action" />
            <input type="submit" value="View your cart" name="action" /></br>
        </form>
    </body>
</html>
