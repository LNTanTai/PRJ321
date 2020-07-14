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
        <h1>Checkout</h1>
        <c:set var="order" value="${requestScope.ORDER}"/>
        <c:if test="${not empty order}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="map" items="${order}" varStatus="counter">
                        <c:set var="dto" value="${map.key}"/>
                        <c:set var="quantity" value="${map.value}"/>
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.ID}</td>
                            <td>${dto.name}</td>
                            <td>${quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <form action="checkoutAction">
                Your name <input type="text" name="txtCusName" value="${param.txtCusName}" /><br/>
                <c:if test="${not empty requestScope.ERROR.nameIsNullError}">
                    <font color="red">
                    ${requestScope.ERROR.nameIsNullError}
                    </font>
                    <br/>
                </c:if>
                Your address <input type="text" name="txtCusAddress" value="${param.txtCusAddress}" /><br/>
                <c:if test="${not empty requestScope.ERROR.addressIsNullError}">
                    <font color="red">
                    ${requestScope.ERROR.addressIsNullError}
                    </font>
                    <br/>
                </c:if>
                <input type="submit" value="Comfirm checkout" />
                <input type="reset" value="Reset" />
            </form>
        </c:if>
        <c:if test="${empty order}">
            No items in your cart
        </c:if>
            <br/>
        <a href="cartView">Change your cart</a>
        
    </body>
</html>
