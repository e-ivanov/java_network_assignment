<%-- 
    Document   : createuser
    Created on : Jul 7, 2015, 7:17:37 AM
    Author     : killer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
                    <form role="form" method="POST">
                            <div class="form-group">
					 
					<label for="exampleInputEmail1">
						Име:
					</label>
                                <input type="text"  name="first_name" class="form-control" id="exampleInputEmail1">
				</div>
                            <div class="form-group">
					 
					<label for="exampleInputEmail1">
						Фамилия:
					</label>
                                <input type="text" name="last_name" class="form-control" id="exampleInputEmail1">
				</div>
                                <div class="form-group">
					 
					<label for="exampleInputEmail1">
						Потребителско име:
					</label>
                                    <input type="text" name="username" class="form-control" id="exampleInputEmail1">
				</div>
				<div class="form-group">
					 
					<label for="exampleInputEmail1">
						Email address
					</label>
                                    <input type="email" name="email" class="form-control" id="exampleInputEmail1">
				</div>
				<div class="form-group">
					 
					<label for="exampleInputPassword1">
						Парола:
					</label>
					<input type="password" name="password" class="form-control" id="exampleInputPassword1">
				</div>
                            <div class="form-group">
					 
					<label for="exampleInputPassword1">
						Повтори паролата:
					</label>
                                <input type="password" name="repeated_password" class="form-control" id="exampleInputPassword1">
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
