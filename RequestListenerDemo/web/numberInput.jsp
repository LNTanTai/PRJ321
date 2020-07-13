<%-- 
    Document   : numberInput
    Created on : Jun 19, 2020, 9:27:57 AM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Number checking</title>
    </head>
    <body>
        <form action="CheckNumberServlet">
            <input type="text" name="txtNumber" value="" />
            <input type="submit" value="Submit" name="btnSubmit" />
        </form>
        <%
            boolean valid = (boolean) request.getAttribute("VALID");
            if (valid) {
                String value = (String) request.getParameter("txtNumber");
                if (value != null) {
        %> 
        <h2><%= value%></h2>
        <%
                }//end if value is not null
            }//end if valid is true

        %>
    </body>
</html>
