<%-- 
    Document   : MobileSearchUser
    Created on : Jun 25, 2020, 8:49:17 AM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <header>
            <h1>Welcome ${USERNAME}</h1>
            <form action="ControllerDispatch">
                <input type="submit" value="Logout" name="action" />
            </form>
        </header>
        <section>
            <form action="ControllerDispatch">
                Minimum Price <input type="text" name="txtMinPrice" value="${param.txtMinPrice}" />
                Maximum Price <input type="text" name="txtMaxPrice" value="${param.txtMaxPrice}" />
                <button type="submit" name="action" value="SearchUser">Search</button>
            </form>
        </section>
                <form action="ControllerDispatch">
                    <input type="submit" value="View Cart" name="action" />
                </form>
        <section>
            <c:set var="minPrice" value="${param.txtMinPrice}"/>
            <c:set var="maxPrice" value="${param.txtMaxPrice}"/>
            <c:if test="${not empty minPrice and not empty maxPrice}">
                <c:set var="listMobile" value="${MOBILE_LIST}"/>
                <c:if test="${not empty listMobile}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Id</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Mobile Name</th>
                                <th>Year Of Production</th>
                                <th>Quantity</th>
                                <th>Not Sale</th>
                                <th>Add to cart</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${listMobile}" varStatus="counter">
                                <tr>
                                    <th>${counter.count}</th>
                                    <th>${dto.mobileId}</th>
                                    <th>${dto.desciption}</th>
                                    <th>${dto.price}</th>
                                    <th>${dto.mobileName}</th>
                                    <th>${dto.yearOfProduction}</th>
                                    <th>${dto.quantity}</th>
                                    <th>${dto.notSale}</th>
                                    <th>
                                        <c:url var="urlRewriting" value="ControllerDispatch">
                                            <c:param name="action" value="AddToCart"/>
                                            <c:param name="pk" value="${dto.mobileId}"/>
                                            <c:param name="lastMinPrice" value="${minPrice}"/>
                                            <c:param name="lastMaxPrice" value="${maxPrice}"/>
                                        </c:url>
                                        <a href="${urlRewriting}">Add to cart</a>
                                    </th>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${empty listMobile}">
                    No search found
                </c:if>
            </c:if>
        </section>
    </body>
</html>
