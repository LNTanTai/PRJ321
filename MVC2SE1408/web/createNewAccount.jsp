<%-- 
    Document   : createNewAccount
    Created on : Jun 26, 2020, 8:20:40 AM
    Author     : Long Le
--%>

<%@page import="longlb.registration.RegistrationCreateNewError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
	<title>Create New Account</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
	<c:set var="createErrors" value="${requestScope.CREATE_ERRORS}" />

	<h1>Create New Account</h1>
	<form action="createNewAccount" method="POST">
	    Username <input type="text" name="txtUsername" value="${param.txtUsername}" /> (e.g 6 - 20 chars) 
	    <c:if test="${not empty createErrors.usernameError}">
		<font color="red">
		    ${createErrors.usernameError}
		</font>
	    </c:if>
	    <c:if test="${not empty createErrors.usernameIsExisted}">
		<font color="red">
		    ${createErrors.usernameIsExisted}
		</font>
	    </c:if>
	    <br/>

	    Password <input type="password" name="txtPassword" value="" /> (e.g 6 - 30 chars)
	    <c:if test="${not empty createErrors.passwordError}">
		<font color="red">
		    ${createErrors.passwordError}
		</font> 
	    </c:if>
	    <br/>

	    Confirm <input type="password" name="txtConfirm" value="" />
	    <c:if test="${not empty createErrors.confirmNotMatched}">
		<font color="red">
		    ${createErrors.confirmNotMatched}
		</font> 
	    </c:if>
	    <br/>

	    Full name <input type="text" name="txtFullname" value="${param.txtFullname}" /> (e.g 2 - 50 chars)
	    <c:if test="${not empty createErrors.fullNameLengthError}">
		<font color="red">
		    ${createErrors.fullNameLengthError}
		</font> 
	    </c:if>
	    <br/>

	    <input type="submit" value="Create New Account" name="btAction" />
	    <input type="reset" value="Reset" />
	</form>
    </body>
</html>
