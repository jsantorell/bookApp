<%-- 
    Document   : authorList
    Created on : Sep 19, 2017, 8:36:00 PM
    Author     : Jeremy Santorelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="197">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Book Web Application</a></li>

            </ul>
        </nav>
        <div class="container">
            <div class="page-header">
                <h1>Author List</h1>
            </div>

            <div class="table-responsive">
                <table class="table">
                    <thead><tr><th>ID</th><th>NAME</th><th>DATE ID</th></tr></thead>
                                <c:forEach var="a" items="${authorList}">

                        <tr>
                            <td>${a.authorId}</td>
                            <td>${a.authorName}</td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${a.dateAdded}"></fmt:formatDate></td>
                            </tr>


                    </c:forEach>
                </table>
            </div>
        </div>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
