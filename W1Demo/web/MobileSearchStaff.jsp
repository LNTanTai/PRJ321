<%-- 
    Document   : MobileSearchStaff
    Created on : Jun 25, 2020, 8:03:57 AM
    Author     : natton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Search</title>
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
                <input type="text" name="txtSearch" value="${param.txtSearch}" />
                <button type="submit" value="SearchStaff" name="action">Search</button>
            </form>
        </section>
        <section>
            <c:set var="searchValue" value="${param.txtSearch}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="searchResult" value="${MOBILE_LIST}"/>
                <c:if test="${not empty searchResult}">
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
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${searchResult}" varStatus="counter">
                            <form action="ControllerDispatch">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>
                                        ${dto.mobileId}
                                        <input type="hidden" name="txtHiddenMobileId" value="${dto.mobileId}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtDescription" value="${dto.desciption}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtPrice" value="${dto.price}" />
                                    </td>
                                    <td>${dto.mobileName}</td>
                                    <td>${dto.yearOfProduction}</td>
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
                                            <c:param name="action" value="Delete"/>
                                            <c:param name="pk" value="${dto.mobileId}"/>
                                            <c:param name="lastSearchValue" value="${searchValue}"/>
                                        </c:url>
                                        <a href="${urlRewritting}">Delete</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Update" name="action" />
                                        <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${empty searchResult}">
                    No search found!!!
                </c:if>
            </c:if>
        </section>

    </body>
</html>
