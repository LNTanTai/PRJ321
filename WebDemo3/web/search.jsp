<%-- 
    Document   : serach
    Created on : Jun 14, 2020, 1:54:29 PM
    Author     : natton
--%>

<%@page import="tientt.login.LoginDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <form action="DipatchController">
            <input type="text" name="txtSearchValue" value="" />
            <input type="submit" value="Search" name="btnAction" />
        </form>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<LoginDTO> result = (List<LoginDTO>) request.getAttribute("SEARCH_RESULT");
                if (result == null) {
        %> 
        <h2>No record found!!!!</h2>
        <%
        } else {
        %> 
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Classname</th>
                    <th>isAdmin</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (LoginDTO dto : result) {
                        String deleteLink = "DipatchController?btnAction=Delete&"
                                + "pk="+dto.getUsername()+"&lastSearchValue="
                                + request.getParameter("txtSearchValue");
                %> 
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                    </td>
                    <td>
                        <%= dto.getPassword()%>
                    </td>
                    <td>
                        <%= dto.getClassname()%>
                    </td>
                    <td>
                        <%= dto.isRole()%>
                    </td>
                    <td>
                        <a href="<%= deleteLink %>">Delete</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
                }
            }
        %>

    </body>
</html>
