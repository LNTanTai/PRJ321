<%-- 
    Document   : ClientSearch
    Created on : Jul 5, 2020, 5:02:52 PM
    Author     : natton
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Page</title>
    </head>
    <body>
        <h1>Welcome, ${sessionScope.USER_FULLNAME}</h1>   
        <form action="ControllerDispatch">
            <input type="submit" value="Logout" name="action" />
        </form>
        <form action="ControllerDispatch">
            Min Price <input type="text" name="txtMinPrice" value="${param.txtMinPrice}" />
            Max Price <input type="text" name="txtMaxPrice" value="${param.txtMaxPrice}" />
            <input type="submit" value="ClientSearch" name="action" />
        </form>
        <c:set var="minPrice" value="${param.txtMinPrice}"/>
        <c:set var="maxPrice" value="${param.txtMaxPrice}"/>
        <c:if test="${not empty minPrice and not empty maxPrice}">
            <c:set var="listMobiles" value="${requestScope.MOBILE_LIST}"/>
            <c:if test="${not empty listMobiles}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>ID</th>
                            <th>Description</th>
                            <th>price</th>
                            <th>Name</th>
                            <th>Year of Production</th>
                            <th>Quantity</th>
                            <th>not sale</th>
                            <th>Add to cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${listMobiles}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.mobileId}</td>
                                <td>${dto.description}</td>
                                <td>${dto.price}</td>
                                <td>${dto.mobileName}</td>
                                <td>${dto.yearofProduction}</td>
                                <td>${dto.quantity}</td>
                                <td>${dto.notSale}</td>
                                <td>
                                    <c:url var="urlRewritting" value="ControllerDispatch">
                                        <c:param name="action" value="AddToCart"/>
                                        <c:param name="txtMinPrice" value="${minPrice}"/>
                                        <c:param name="txtMaxPrice" value="${maxPrice}" />
                                        <c:param name="id" value="${dto.mobileId}"/>
                                    </c:url>
                                    <a href="${urlRewritting}">Add to Cart</a>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty listMobiles}" >
                No Mobile was found!!!!
            </c:if>
        </c:if>

    </body>
</html>
