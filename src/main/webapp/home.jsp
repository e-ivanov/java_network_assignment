<%-- 
    Document   : photos
    Created on : Jul 7, 2015, 7:35:15 AM
    Author     : killer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    <%@ include file="WEB-INF/jspf/head.jspf"%>
    </head>
    <body>

        <div class="container-fluid">
            <%@include file="WEB-INF/jspf/navigation.jspf" %>
            <div class="row">
                <c:forEach items="${books}" var="book">
                    <p>${book.getTitle()}</p>
                </c:forEach>
            </div>
        </div>
    

    <%@include file="WEB-INF/jspf/jsfrag.jspf" %>
</body>
</html>
