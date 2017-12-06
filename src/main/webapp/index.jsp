<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jrs.bookwebapp.model.Author"%>
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
        <jsp:include page="include/head.jsp" />
        <title>Book Web Application</title>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
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
                                    ${a.title}</td>
                                <td style="text-align:center;"><a href="https://isbnsearch.org/isbn/${a.isbn}" target="_blank">${a.isbn}</a></td>
                                <td style="text-align:center;">${a.authorId.authorName}</td>

                            </tr>

                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>

        <jsp:include page="include/footer.jsp" />
        <jsp:include page="include/scripts.jsp" />
    </body>
</html>
