<%-- 
    Document   : books
    Created on : Jan 12, 2016, 9:53:37 PM
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
                <c:forEach items="${books}" var="book">
                    <p><a href="<%= request.getContextPath()%>/book/view/id/${book.getId()}">${book.getTitle()}</a></p>
                </c:forEach>
            </div>
        </div>
            <%@include file="WEB-INF/jspf/jsfrag.jspf" %>
            
    </body>
</html>
