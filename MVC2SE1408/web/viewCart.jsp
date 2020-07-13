<%-- 
    Document   : viewCart
    Created on : Jun 19, 2020, 7:23:07 AM
    Author     : Long Le
--%>

<%@page import="java.util.Map"%>
<%@page import="longlb.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Store</title>
    </head>
    <body>
        <h1>Your Cart</h1>
	<%
	    //1. Customer goes to cart place
	    if (session != null) {
		//2. Customer takes cart 
		CartObject cart = (CartObject) session.getAttribute("CART");

		if (cart != null) {
		    //3. Get items (khong gian chua cua gio)
		    Map<String, Integer> items = cart.getItems();

		    if (items != null) {
	%>
	<table border="1">
	    <thead>
		<tr>
		    <th>No.</th>
		    <th>Title</th>
		    <th>Quantity</th>
		    <th>Action</th>
		</tr>
	    </thead>
	    <form action="removeBookFromCart">
		<tbody>
		    <%
			int count = 0;
			for (String title : items.keySet()) {
		    %>

		    <tr>
			<td><%= ++count%></td>
			<td><%= title%></td>
			<td><%= items.get(title)%></td>	    
			<td>
			    <input type="checkbox" name="chkItem" value=<%= title%> />
			</td>	    
		    </tr>
		    <%
			} //end for title
		    %>
		    <tr>
			<td colspan="3">
			    <a href="shopping">Add More Books To Your Cart</a>
			</td>
			<td>
			    <input type="submit" value="Remove Selected Books" name="btAction" />
			</td>
		    </tr>
		</tbody>
	    </form>
	</table>
	<%
	    String urlRewriting = "checkoutCart";
//		    + "?btAction=Checkout Cart";
	%>
	<a href="<%= urlRewriting%>">Checkout the cart</a>  

	<%
		}//end if items existed
	    } //end if cart existed
	    else {
	%>
	    <h2>
		No cart is existed
	    </h2>
	<%
		}
	    }//cart place is existed


	%>


    </body>
</html>
