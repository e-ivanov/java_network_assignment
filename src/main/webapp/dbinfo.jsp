<%-- 
    Document   : dbinfo
    Created on : Jan 12, 2016, 11:51:23 PM
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
        </div>
            <%@include file="WEB-INF/jspf/jsfrag.jspf" %>
            <div class="row">
                <c:forEach items="${databases}" var="database">
                    <p><a href="<%= request.getContextPath()%>/dbcontroller/showtableinfo/dbname/${database}">${database}</a></p>
                </c:forEach>
            </div>
    </body>
</html>
