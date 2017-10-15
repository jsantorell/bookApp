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

        <title>Author List</title>
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
                <li role="presentation"><a href="index.jsp"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                <li role="presentation" class="active"><a href="authorController?action=list"><i class="fa fa-cogs" aria-hidden="true"></i> Manage Authors</a></li>

            </ul>
            <div class="wrapper">

                <h3>Manage Author List</h3>
                <div>${rowsAffected}</div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Author ID</th>
                            <th style="text-align:center;">Author Name</th>
                            <th style="text-align:center;">Date Added</th>
                            <th style="text-align:center;"></th>
                            <th style="text-align:center;"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>

                            <td><form class="col-sm-12" method="POST" id="authorForm" action="authorController?action=tryadd">

                                    <div class="form-group">
                                        <label for="AuthorName">Add Author:</label></td>
                                        <td> 
                                            <input type="text" class="form-control" id="AuthorName" name="AuthorName">
                                    </div>
                            </td>
                            <td></td>
                            <td><button type="submit" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i></button>

                                </form></td>
                            <td></td>

                        </tr>
                        <c:forEach var="a" items="${authorList}">

                            <tr>
                                <th scope="row">${a.authorId}</td>
                                <td> <form class="col-sm-12" method="POST" id="authorForm" action="authorController?action=tryupdate&id=${a.authorId}">
                                        <input type="text" class="form-control" id="${a.authorId}" name="${a.authorId}" value="${a.authorName}"></td>
                                        <td style="text-align:center;"><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.dateAdded}" /></td>
                                        <td>

                                            <button type="submit" class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i></button>
                                    </form>
                                </td>
                                <td>
                                    <form class="col-sm-12" method="POST" id="authorForm" action="authorController?action=trydelete&id=${a.authorId}">
                                        <button type="submit" class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></button>
                                    </form>
                                </td>
                            </tr>

                        </c:forEach>

                        <tr>

                            <td><form class="col-sm-12" method="POST" id="authorForm" action="authorController?action=tryadd">

                                    <div class="form-group">
                                        <label for="AuthorName">Add Author:</label></td>
                                        <td> 
                                            <input type="text" class="form-control" id="AuthorName" name="AuthorName">
                                    </div>
                            </td>
                            <td></td>
                            <td><button type="submit" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i></button>

                                </form></td>
                            <td></td>

                        </tr>

                    </tbody>
                </table>

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
