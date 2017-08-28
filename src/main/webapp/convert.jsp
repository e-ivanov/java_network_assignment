<%-- 
    Document   : convert
    Created on : Jan 12, 2016, 10:47:24 PM
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
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <form enctype="multipart/form-data"method="POST" role="form" action="<%= request.getContextPath()%>/converter/convert">

                        <div class="form-group">
                            <label for="tobeconverted">
                                Примерна глава:
                            </label>
                            <input type="file" name="tobeconverted" class="form-control" id="tobeconverted">
                        </div>
                        <button type="submit" class="btn btn-default">
                            Submit
                        </button>
                    </form>
                </div>
                <div class="col-md-4">
                </div>
            </div>
        </div>

        <script src="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/js/jquery.min.js"></script>
        <script src="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/js/bootstrap.min.js"></script>
        <script src="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/js/scripts.js"></script>
    </body>
</html>
