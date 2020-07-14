<%-- 
    Document   : search
    Created on : Jul 13, 2020, 8:50:47 PM
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
        <h1>Welcome, ${sessionScope.USER.fullname}</h1>
        <form action="logoutAction">
            <input type="submit" value="Logout" />
        </form>
        <form action="searchAction">
            Seach <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" />
        </form>
        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="accountList" value="${sessionScope.ACCOUNT_LIST}"/>
            <c:if test="${not empty accountList}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Fullname</th>
                            <th>isAdmin</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${accountList}" varStatus="counter">
                        <form action="updateAction">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtHiddenUsername" value="${dto.username}" />
                                </td>
                                <td>

                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>${dto.fullname}</td>
                                <td>
                                    <input type="checkbox" name="chkRole" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewritting" value="deleteAction">
                                        <c:param name="txtLastSearchValue" value="${searchValue}"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                    </c:url>
                                    <a href="${urlRewritting}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="txtLastSearchValue" value="${searchValue} " />
                                    <input type="submit" value="Update" />
                                </td>

                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty accountList}">
            No account found
        </c:if>
    </c:if>

</body>
</html>
