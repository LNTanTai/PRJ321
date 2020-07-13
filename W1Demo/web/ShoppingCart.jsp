<%-- 
    Document   : ShoppingCart
    Created on : Jun 25, 2020, 9:20:53 PM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${USERNAME}'s Cart</title>
    </head>
    <body>
        <c:set var="listMobile" value="${requestScope.MOBILE_LIST}"/>
        <c:set var="items" value="${sessionScope.CART.items}"/>
        <c:if test="${not empty items and not empty listMobile}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                <form action="ControllerDispatch">
                    <c:forEach var="dto" items="${listMobile}" varStatus="counter">
                        <%--<c:set var="id" value="${dto.mobileId}"/>--%>
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.mobileId}</td>
                            <td>${dto.mobileName}</td>
                            <td>${dto.price}</td>
                            <td>${items[dto.mobileId]}</td>   
                            <td>
                                <input type="checkbox" name="chkDelete" value="${dto.mobileId}" />
                            </td>
                        </tr>
                    </c:forEach>
                        <tr>
                            <td colspan="5">Delete selected items</td>
                            <td>
                                <button type="submit" name="action" value="DeleteItemInCart">
                                    Delete
                                </button>
                            </td>
                        </tr>
                </form>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty items or empty listMobile}">
        Cart is empty
    </c:if>
    <a href="ControllerDispatch?action=SearchUser">Continue shopping</a>
</body>
</html>
