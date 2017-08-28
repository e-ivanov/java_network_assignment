<%-- 
    Document   : book
    Created on : Jan 11, 2016, 11:14:21 PM
    Author     : killer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="WEB-INF/jspf/head.jspf"%>
        <script src="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/js/pdfobject.js"></script>
        <script type="text/javascript">
            window.onload = function () {
                var success = new PDFObject({url: "<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/files/${book.getSampleChapterLocation()}"}).embed("pdf");
            };
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="WEB-INF/jspf/navigation.jspf" %>
            <p>Заглавие: ${book.getTitle()}</p>
            <p><img src="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/files/${book.getCover()}"}" /></p>
            <p>ISBN: ${book.getIsbn()}</p>
            <p>Автори: ${book.getAuthors()}</p>
            <p>Съдържание: ${book.getTableOfContents()}</p>
            <div id="pdf"></div>
            <p><a href="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/files/${book.getExtraResourcesLocation()}"}">Допълнителни ресурси</a> </p>
            <p><a href="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/files/${book.getSampleCodeLocation()}"}">Примерен код</a></p>
            <p>Errata: ${book.getErrata()}</p>
        </div>
        <%@include file="WEB-INF/jspf/jsfrag.jspf" %>
    </body>
</html>
