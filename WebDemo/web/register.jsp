<%-- 
    Document   : register
    Created on : Jun 26, 2020, 8:17:07 AM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <form action="registerAction" method="POST">
            <c:set var="result" value="${requestScope.ERROR}" />
            Username <input type="text" name="txtUsername" value="${param.txtUsername}" /><br/>
            <c:if test="${not empty result.usernameLengthError}">
                <font color="red">
                    ${result.usernameLengthError}
                </font>
                <br/>
            </c:if>
            <c:if test="${not empty result.usernameIsExisted}">
                <font color="red">
                    ${result.usernameIsExisted}
                </font>
                <br/>
            </c:if>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty result.passwordLengthError}">
                <font color="red">
                    ${result.passwordLengthError}
                </font>
                <br/>
            </c:if>
            Confirm Password <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty result.confirmNotMatchError}">
                <font color="red">
                    ${result.confirmNotMatchError}
                </font>
                <br/>
            </c:if>
            Class name <input type="text" name="txtClassName" value="${param.txtClassName}" /><br/>
            <c:if test="${not empty result.classnameLengthError}">
                <font color="red">
                    ${result.classnameLengthError}
                </font>
                <br/>
            </c:if>
            <input type="submit" value="Register" name="btnLogin" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
