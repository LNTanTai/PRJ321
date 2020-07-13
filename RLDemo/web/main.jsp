<%-- 
    Document   : main
    Created on : Jun 23, 2020, 5:38:02 AM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page1</title>
    </head>
    <body>
        <jsp:useBean id="sample" class="tientt.bean.SampleBean" scope="page"/>
        <jsp:setProperty name="sample" property="name" value="Tien"/>
        <h1>Page 1: ${sample.getName()}</h1>
        <%@include file="sub.jsp"%>
    </body>
</html>
