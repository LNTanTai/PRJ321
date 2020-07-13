<%-- 
    Document   : onlineStore
    Created on : Jun 17, 2020, 2:05:16 PM
    Author     : longlb88
--%>

<%@page import="java.util.List"%>
<%@page import="longlb.product.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Shopping Online</h1>
	<form action="addItemToCart">
	    Choose book <select name="cboBook">
		<%
		    List<String> listProduct = (List<String>) session.getAttribute("LIST_PRODUCT");
		    for (String product : listProduct) {
		%>
		<option><%= product%></option>
		<%  
		    }
		%>	
	    </select> <br/>
	    <input type="submit" value="Add book to your cart" name="btAction" />
	</form>
	<form action="viewCart.jsp">
	    <input type="submit" value="View your cart" name="btAction" />
	</form>
    </body>
</html>
