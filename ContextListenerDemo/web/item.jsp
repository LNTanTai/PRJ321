<%-- 
    Document   : item
    Created on : Jun 18, 2020, 2:04:44 PM
    Author     : natton
--%>

<%@page import="tientt.item.ItemDTO"%>
<%@page import="java.util.List"%>
<%@page import="tientt.utils.DBHelpers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Books</title>
    </head>
    <body>
        <%
            int count = 0;
            List<ItemDTO> result = (List<ItemDTO>) request.getAttribute("BOOK_LIST");
            if (result != null) {
        %> 
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (ItemDTO dto : result) {
                %> 
                <tr>
                    <td><%= ++count%></td>
                    <td><%= dto.getID()%></td>
                    <td><%= dto.getName()%></td>
                    <td><%= dto.getPrice()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
        } //end if result is not null
%>
    </table>

</body>
</html>
