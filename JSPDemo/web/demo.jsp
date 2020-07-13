<%-- 
    Document   : demo
    Created on : Jul 7, 2020, 10:11:17 PM
    Author     : natton
--%>

<%@page import="bean.DemoBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            RequestDispatcher rd = request.getRequestDispatcher("demo2.jsp");
            rd.include(request, response);  
        %>
    </body>
</html>
