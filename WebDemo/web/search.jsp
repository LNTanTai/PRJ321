<%-- 
    Document   : search
    Created on : Jun 10, 2020, 8:23:48 AM
    Author     : natton
--%>

<%@page import="tientt.login.LoginDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <h1>
            Welcome ${sessionScope.USER["fullName"]}  
        </h1>
        <form action="logoutAction">
            <input type="submit" value="Logout" name="Logout" />
        </form>
        <form action="searchAction">
            Search Value <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" name="btnLogin" />
        </form> </br>
        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Fullname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccountAction">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtHiddenUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                    <c:if test="${not empty requestScope.ERRORS.passwordIsNullError 
                                                  and requestScope.ERROR_PK == dto.username }" >
                                          </br>
                                          <font color="red">
                                          ${requestScope.ERRORS.passwordIsNullError}
                                          </font>
                                    </c:if>
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="deleteAccountAction">
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="update" name="btnLogin" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            No search found!!!
        </c:if>
    </c:if>
    <%--<div>
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie lastCookie = cookies[cookies.length - 1];
                String username = lastCookie.getName();
        %> 
        <h2 style="color: red">Welcome <%= username%></h2>
        <%
            }//end if cookies is not null
        %>
        <form action="DispatchController">
            <input type="submit" value="logout" name="btnLogin" />
        </form>
    </div>
    <form action="DispatchController">
        Search Value <input type="text" name="txtSearch" value="" />
        <input type="submit" value="Search" name="btnLogin" />
    </form>
    <br/>
    <%
        String searchValue = request.getParameter("txtSearch");
        if (searchValue != null) {
            List<LoginDTO> result = (List<LoginDTO>) request.getAttribute("SEARCH_RESULT");
            if (result == null) {
    %>
    <h2>
        No record match!!!!
    </h2>
    <%
    } else {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Username</th>
                <th>Password</th>
                <th>Full name</th>
                <th>Role</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 0;
                for (LoginDTO dto : result) {
                    String urlRewriting = "DispatchController"
                            + "?btnLogin=delete"
                            + "&pk=" + dto.getUsername()
                            + "&lastSearchValue=" + searchValue;
            %> 
        <form action="DispatchController">
            <tr>
                <td>
                    <%= ++count%>    
                </td>
                <td>
                    <%= dto.getUsername()%> 
                    <input type="hidden" name="txtHiddenUsername" 
                           value="<%= dto.getUsername()%>" />
                </td>
                <td>
                    <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                </td>
                <td>
                    <%= dto.getFullName()%>
                </td>
                <td>
                    <input type="checkbox" name="chkAdmin" value="ON" 
                           <%
                               if (dto.isRole() == true) {
                           %> 
                           checked="checked"
                           <%
                               }//end if is role
                           %>
                           />
                </td>
                <td>
                    <a href="<%= urlRewriting%>">Delete</a>
                </td>
                <td>
                    <input type="submit" value="Update" name="btnLogin" />
                    <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                </td>
            </tr>
        </form>    
        <%
            } //end for dto
        %>
    </tbody>
</table>

    <%
            }
        }//end if searchValue is not null
%>>--%>
</body>
</html>
