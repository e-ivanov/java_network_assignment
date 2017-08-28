<%-- 
    Document   : upload
    Created on : Jul 7, 2015, 7:29:30 AM
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
                    <form enctype="multipart/form-data"method="POST" role="form" action="<%= request.getContextPath()%>/book/create"
                          >

                        <div class="form-group">

                            <label for="bookTitle">
                                Заглавие:
                            </label>
                            <input type="text" name="bookTitle" class="form-control" id="bookTitle">
                        </div>
                        <div class="form-group">
                            <label for="isbn">
                                ISBN:
                            </label>
                            <input type="text" name="isbn" class="form-control" id="isbn">
                        </div>
                        <div class="form-group">
                            <label for="authors">
                                Автори:
                            </label>
                            <input type="text" name="authors" class="form-control" id="authors">
                        </div>
                        <div class="form-group">
                            <label for="bookCover">
                                Обложка:
                            </label>
                            <input type="file" name="bookCover" class="form-control" id="bookCover">
                        </div>
                        <div class="form-group">
                            <label for="tableOfContents">
                                Съдържание:
                            </label>
                            <textarea id="tableOfContents" name="tableOfContents" class="form-control" rows="6" cols="50">
                                
                            </textarea>
                        </div>
                        <div class="form-group">
                            <label for="errata">
                                Корекции към книгата:
                            </label>
                            <textarea id="errata" name="errata" class="form-control" rows="6" cols="50">
                                
                            </textarea>
                        </div>
                        <div class="form-group">
                            <label for="sampleChapterLocation">
                                Примерна глава:
                            </label>
                            <input type="file" name="sampleChapterLocation" class="form-control" id="sampleChapterLocation">
                        </div>
                        <div class="form-group">
                            <label for="extraResourcesLocation">
                                Ресурси към книгата:
                            </label>
                            <input type="file" name="extraResourcesLocation" class="form-control" id="extraResourcesLocation">
                        </div>
                        <div class="form-group">
                            <label for="sampleCodeLocation">
                                Примерен код:
                            </label>
                            <input type="file" name="sampleCodeLocation" class="form-control" id="sampleCodeLocation">
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
