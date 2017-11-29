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
        <jsp:include page="include/head.jsp" />
        <title>Author List</title>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <div class="container">

            <ul class="nav nav-tabs">
                <li role="presentation"><a href="authorController?action=index"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
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
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="${a.authorId}" name="${a.authorId}" value="${a.authorName}" style="display: inline-block;">
                                            <span class="input-group-btn">                                                
                                                <button type="button" class="btn btn-primary" onclick="window.location = 'bookController?action=list&id=${a.authorId}'"><i class="fa fa-plus" aria-hidden="true"></i> <i class="fa fa-book" aria-hidden="true"></i></button>
                                            </span>

                                        </div>




                                </td>
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
        <jsp:include page="include/footer.jsp" />
        <jsp:include page="include/scripts.jsp" />
    </body>
</html>
