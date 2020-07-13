<%-- 
    Document   : StaffSearch
    Created on : Jul 5, 2020, 5:00:26 PM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
    </head>
    <body>
        <h1>Welcome, ${sessionScope.USER_FULLNAME}</h1>
        <form action="ControllerDispatch">
            <input type="submit" value="Logout" name="action" />
        </form>
        <form action="ControllerDispatch">
            Search with ID or Name <br/>
            <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <button type="submit" value="StaffSearch" name="action">Search</button>
        </form>
        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="mobileList" value="${requestScope.LIST_MOBILES}"/>
            <c:if test="${not empty mobileList}">
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
                            <th>Delete</th>
                            <th>Update</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${mobileList}" varStatus="counter">
                        <form action="ControllerDispatch">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.mobileId}
                                    <input type="hidden" name="txtHiddenID" value="${dto.mobileId}" />
                                </td>
                                <td>
                                    <input type="text" name="txtDescription" value="${dto.description}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPrice" value="${dto.price}" />
                                </td>
                                <td>${dto.mobileName}</td>
                                <td>${dto.yearofProduction}</td>
                                <td>
                                    <input type="text" name="txtQuantity" value="${dto.quantity}" />
                                </td>
                                <td>
                                    <input type="checkbox" name="chkNotSale" value="ON"
                                           <c:if test="${dto.notSale}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewritting" value="ControllerDispatch">
                                        <c:param name="action" value="DeleteMobile"/>
                                        <c:param name="id" value="${dto.mobileId}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}" />
                                    </c:url>
                                    <a href="${urlRewritting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="UpdateMobile" name="action" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty mobileList}">
            No mobile was found!!!!
        </c:if>
    </c:if>

</body>
</html>
