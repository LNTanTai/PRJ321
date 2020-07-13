<%-- 
    Document   : cart
    Created on : Jul 11, 2020, 8:25:14 AM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:set var="items" value="${requestScope.MAP_DTO}"/>

        <c:if test="${not empty items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                <form action="cartDeleteItem">
                    <c:forEach var="map" items="${items}" varStatus="counter">
                        <c:set var="dto" value="${map.key}"/>
                        <c:set var="quantity" value="${map.value}"/>
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.name}</td>
                            <td>${quantity}</td>
                            <td>
                                <input type="checkbox" name="chkDelete" value="${dto.ID}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">
                            <a href="checkout">Checkout</a>
                        </td>
                        <td>
                            <input type="submit" value="Delete selected items" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty items}">
        No items in your cart!!!! 
    </c:if>
    <br/>
    <a href="storeView">Continue shopping</a>
</body>
</html>
