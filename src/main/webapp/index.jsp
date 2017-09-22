<%-- 
    Document   : index
    Created on : Sep 19, 2017, 8:00:30 PM
    Author     : Jeremy Santorelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Demo App</title>

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
                <h1>Pick Administrative Task</h1>
            </div>

            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" class="active"><a href="/BookWebApp/author?action=list"> List of Authors </a></li>
                <li role="presentation"><a href="#"> More to come... </a></li>

            </ul>
        </div>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
