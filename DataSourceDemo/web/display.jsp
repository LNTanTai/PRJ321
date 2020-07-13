<%-- 
    Document   : display
    Created on : Jun 19, 2020, 5:40:03 AM
    Author     : natton
--%>

<%@page import="java.util.List"%>
<%@page import="tientt.item.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
