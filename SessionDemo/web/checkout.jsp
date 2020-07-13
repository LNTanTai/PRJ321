<%-- 
    Document   : checkout
    Created on : Jun 21, 2020, 10:01:18 AM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out</title>
    </head>
    <body>
        <h1>Welcome <%= (String)session.getAttribute("USERNAME") %></h1>
        <h2>Checkout ID: <%= (Integer)session.getAttribute("CHECKOUT_ID") %> </h2>
        <h2>Check out successfully</h2>
        <a href="DispatchController?action=DisplayShoppingPage">Click here to continue shopping</a>
    </body>
</html>
