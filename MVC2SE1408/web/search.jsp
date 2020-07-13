<%-- 
    Document   : search
    Created on : Jun 10, 2020, 8:24:07 AM
    Author     : Long Le
--%>

<%@page import="longlb.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page session="false"%>--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
	<font color="red">
	    Welcome, ${sessionScope.FULLNAME} 
	</font>
	
	<form action="logout">
	    <input type="submit" value="Logout" name="btAction" />
	</form>
	
	<h1>Search page</h1>
	<form action="search">
	    Search Value<input type="text" name="txtSearch" value="${param.txtSearch}" />
	    <input type="submit" value="Search" name="btAction" />
	    <br/>
	</form>
	<br/>
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
			    <th>Full name</th>
			    <th>Role</th>
			    <th>Delete</th>
			    <th>Update</th>
			</tr>
		    </thead>
		    <tbody>
			<c:forEach var="dto" items="${result}" varStatus="counter">
			<form action="updatePassRole">
			    <tr>
				<td>
				    ${counter.count}
				</td>
				<td>
				    ${dto.username}
				    <input type="hidden" name="txtUsername" value="${dto.username}" />
				</td>
				<td>
				    <input type="text" name="txtPassword" value="${dto.password}" />
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
				    <c:url var="urlRewriting" value="deleteAccount">
					<c:param name="btAction" value="delete"/>
					<c:param name="username" value="${dto.username}"/>
					<c:param name="lastSearchValue" value="${searchValue}"/>
				    </c:url>
				    <a href="${urlRewriting}">Delete</a>
				</td>
				<td>
				    <input type="submit" value="Update" name="btAction" />
				    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
				</td>
			    </tr>
			</form>
		    </c:forEach>
		</tbody>
	    </table>

	</c:if>
	<c:if test="${empty result}">
	    No record is matched!!!
	</c:if>
    </c:if>


    <%--<%
	HttpSession curSession = request.getSession(false);
	if (curSession == null){
	    String redirectURL = "login.html";
	    response.sendRedirect(redirectURL);
	}//end if curSession is not exist
	
	String username = (String) curSession.getAttribute("USERNAME");
	
//	    Cookie[] cookies = request.getCookies();
//	    if (cookies != null) {
//		Cookie lastCookie = cookies[cookies.length - 1];
//		String username = lastCookie.getName();
    %>
    <font color="red">
	Welcome, <%= username %>
    </font>
    <%
//	    }//end if cookies existed
    %>
    
    <h1>Search page</h1>
    <form action="DispatchController">
	Search Value<input type="text" name="txtSearch" value="" />
	<input type="submit" value="Search" name="btAction" />
	<br/>
    </form>
    <%
	String searchValue = request.getParameter("txtSearch");

	    if (searchValue != null) {
		List<RegistrationDTO> result
			= (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");

		if (result == null) {
	%>
	<h2>
	    No record is matched!!
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
		    for (RegistrationDTO dto : result) {
			String urlRewriting = "DispatchController"
				+ "?btAction=delete"
				+ "&username=" + dto.getUsername()
				+ "&lastSearchValue=" + searchValue;
		%>
	    <form action="DispatchController">
		<tr>
		    <td>
			<%= ++count%>
		    </td>
		    <td>
			<%= dto.getUsername()%>
			<input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
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
				   if (dto.isRole()) {
			       %>
			       checked="checked"
			       <%
				   } //end if is role

			       %>
			       /> 
		    </td>
		    <td>
			<a href="<%= urlRewriting%>">Delete</a>
		    </td>
		    <td>
			<input type="submit" value="Update" name="btAction" />
			<input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
		    </td>
		</tr>
	    </form>


	    <%
		}//end of for dto
	    %>

	</tbody>
    </table>
    
    <%
	    }
	}//end if search value is not null
%>
    <br/>
    <form action="DispatchController">
	<input type="submit" value="Logout" name="btAction" />
    </form>--%> 
</body>
</html>
