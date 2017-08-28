<%-- 
    Document   : login
    Created on : Jul 7, 2015, 7:24:37 AM
    Author     : killer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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
                    <form method=post role="form" action="j_security_check">
                        <div class="form-group">

                            <label for="exampleInputEmail1">
                                Email address
                            </label>
                            <span>Username:</span> <br /> <input type="text" name="j_username">
                        </div>
                        <div class="form-group">

                            <label for="exampleInputPassword1">
                                Password
                            </label>
                            <span>Password:</span> <br /> <input type="password"
                                                                 name="j_password">
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
            <%@include file="WEB-INF/jspf/jsfrag.jspf" %>
    </body>
</html>
