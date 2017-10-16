<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Author"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">


        <link rel="stylesheet" href="css/bootstrap.min.css">
        <style>
            body {
                padding-top: 50px;
                padding-bottom: 20px;
            }
        </style>
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
        <link href="font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>


        <title>Book Web Application</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        <img alt="Book Web App" src="...">
                    </a>

                </div>
            </div>
        </nav>
        <div class="container">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="authorController?action=index"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                <li role="presentation"><a href="authorController?action=list"><i class="fa fa-cogs" aria-hidden="true"></i> Manage Authors</a></li>

            </ul>
            <div class="wrapper col-sm-12">
                <h3>Welcome</h3>
                <p>Home           - Look up an Author in the database</p>
                <p>Manage Authors - Allows you to add, update, and delete Authors</p>


                <h3 style="text-align:center;">Find an Author</h3>
                <form method="POST" id="authorForm" action="authorController?action=trylookup">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Ernest Hemingway" name="theSearch" aria-describedby="basic-addon2">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                        </span>

                    </div>
                </form>
                <c:if test="${fn:length(authorList) > 0}">



                    <table class="table table-striped">
                        <thead>
                            <tr>
                               
                                <th style="text-align:center;">Author Name</th>
                                <th style="text-align:center;">Date Added</th>
                           
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="a" items="${authorList}">

                                <tr>
                                   
                                    <td> 
                                        ${a.authorName}</td>
                                    <td style="text-align:center;"><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.dateAdded}" /></td>
                         
                                </tr>

                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
        <nav class="navbar navbar-inverse navbar-fixed-bottom">
            <div class="container-fluid">
                <div class="navbar-header navbar-right">
                    <a class="navbar-brand" href="#">
                        <img alt="Book Web App" src="...">
                    </a>
                </div>
            </div>
        </nav>
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    </body>
</html>
