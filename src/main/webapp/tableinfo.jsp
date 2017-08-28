<%-- 
    Document   : tableinfo
    Created on : Jan 12, 2016, 11:52:11 PM
    Author     : killer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="WEB-INF/jspf/navigation.jspf" %>
            <div class="row">
                <c:forEach items="${tables}" var="table">
                    <p>${table.getName()}${table.getColumns().toString()}</p>
                </c:forEach>
            </div>
        </div>
            <%@include file="WEB-INF/jspf/jsfrag.jspf" %>
    </body>
</html>
