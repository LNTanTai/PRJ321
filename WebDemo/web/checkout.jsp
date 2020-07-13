<%-- 
    Document   : checkout
    Created on : Jul 11, 2020, 9:45:03 AM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <form action="cartCheckout">
            Your name <input type="text" name="txtCusName" value="${param.txtCusName}" /><br/>
            <c:if test="${not empty requestScope.ERROR.nameIsNullError}">
                <font color="red">
                ${requestScope.ERROR.nameIsNullError}
                <br/>
                </font>
            </c:if>
            Your address <input type="text" name="txtCusAddress" value="${param.txtCusAddress}" /><br/>
            <c:if test="${not empty requestScope.ERROR.addressIsNullError}">
                <font color="red">
                ${requestScope.ERROR.addressIsNullError}
                <br/>
                </font>
            </c:if>
            <input type="submit" value="Checkout" />
            <input type="reset" value="Reset" />
        </form>
        <a href="storeView">Continue shopping</a>
    </body>
</html>
