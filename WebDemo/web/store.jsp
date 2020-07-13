<%-- 
    Document   : onlineStore
    Created on : Jun 17, 2020, 11:23:56 AM
    Author     : natton
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <h1>Shopping online</h1>
    <c:set var="listItem" value="${ITEM_LIST}"/>
    <c:if test="${not empty listItem}">
        <form action="cartAddItem">
        Choose book <select name="item">
            <c:forEach var="dto" items="${listItem}">
                <option value="${dto.ID}">
                    ${dto.name}
                </option>
            </c:forEach>
        </select></br>
        <input type="submit" value="Add to cart" name="btnLogin" />
        <a href="cartView">View your cart</a>
    </form>
    </c:if>
    <c:if test="${empty listItem}">
        <h2>No product</h2>
    </c:if>
    
</html>
