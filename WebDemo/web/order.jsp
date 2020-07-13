<%-- 
    Document   : order
    Created on : Jul 11, 2020, 11:25:18 AM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <h1>Order successfully!!!</h1>
        <c:set var="order" value="${requestScope.ORDER_METADATA}"/>
        <h2>Order number: ${order.checkoutID}</h2>
        Receiver: ${order.cusName}<br>
        Address: ${order.cusAddress}
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
                <c:forEach var="item" items="${requestScope.ORDER_DETAIL}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${item.itemID}</td>
                        <td>Name</td>
                        <td>Quantity</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
