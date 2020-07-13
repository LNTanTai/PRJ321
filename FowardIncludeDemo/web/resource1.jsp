<%-- 
    Document   : resource1
    Created on : Jun 21, 2020, 9:41:44 PM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resource1</title>
    </head>
    <body>
        <h1>This is the head of resource1</h1>
        <jsp:include page="resource2.jsp" />
        <% // 
//            RequestDispatcher rd = request.getRequestDispatcher("resource2.jsp");
//            rd.forward(request, response);
        %>
        <h1>This is the end of resource1</h1>
        
    </body>
</html>
