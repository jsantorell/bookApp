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
        <link rel="stylesheet" href="css/stylings.css">

        <title>Edit Author</title>
    </head>
    <body>
        <div class="container">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="index.jsp">Home</a></li>
                <li role="presentation"><a href="authorController?action=list">View/Delete Authors</a></li>
                <li role="presentation"><a href="authorController?action=add">Add an Author</a></li>
                <li role="presentation" class="active"><a href="authorController?action=update">Update an Author</a></li>
            </ul>
            <h1>Edit Author</h1>
            <div>${rowsAffected}</div>
            <form class="col-sm-12" method="POST" id="authorForm" action="authorController?action=tryupdate">
                <div class="form-group">
                    <label for="AuthorName">Author's ID</label>
                    <input type="text" class="form-control" id="AuthorID" name="AuthorID">
                </div>
                <div class="form-group">
                    <label for="AuthorName">Author's Name</label>
                    <input type="text" class="form-control" id="AuthorName" name="AuthorName">
                </div>
                <div class="form-group">
                    <label for="AuthorName">Date Added</label>
                    <input type="text" class="form-control" id="DateAdded" name="DateAdded">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>

            </form>
        </div>
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    </body>
</html>
