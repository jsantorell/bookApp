<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <li role="presentation" class="active"><a href="index.jsp"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                <li role="presentation"><a href="authorController?action=list"><i class="fa fa-cogs" aria-hidden="true"></i> Manage Authors</a></li>

            </ul>
            <div class="wrapper">
                <h3>Welcome</h3>
                <p>Manage Authors - Allows you to add, update, and delete Authors</p>
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
