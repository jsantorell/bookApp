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
        <title>Author List</title>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <div class="container">

            <ul class="nav nav-tabs">
                <li role="presentation"><a href="authorController?action=index"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                <li role="presentation"><a href="authorController?action=list"><i class="fa fa-cogs" aria-hidden="true"></i> Manage Authors</a></li>


            </ul>
            <div class="wrapper">

                <h3>Manage Books For ${author}</h3>
                <div>${rowsAffected}</div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Book ID</th>
                            <th style="text-align:center;">Book Name</th>
                            <th style="text-align:center;">ISBN</th>
                            <th style="text-align:center;"></th>
                            <th style="text-align:center;"></th>
                            <th style="text-align:center;"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td></td>
                            <td>
                                <form class="col-sm-12" method="POST" id="authorForm" action="bookController?action=tryadd">

                                    <div class="form-group">
                                        <label for="bookName">Add Book:</label>

                                        <input type="text" class="form-control" id="bookName" name="bookName">

                                        <input type="hidden" value="${hiddenId}" name="authorId">
                                    </div>
                            </td>

                            <td> 
                                <div class="form-group">
                                    <label for="isbn">ISBN:</label>

                                    <input type="text" class="form-control" id="bookISBN" name="bookISBN">
                                </div>
                            </td>
                            <td> 

                            </td>
                            <td><button type="submit" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i></button>

                                </form></td>
                            <td></td>

                        </tr>
                        <c:forEach var="b" items="${bookList}">

                            <tr>
                                <td scope="row">${b.bookId}</td>
                                <td> <form class="col-sm-12" method="POST" id="bookForm" action="bookController?action=tryupdate&id=${b.bookId}">
                                        <input type="text" class="form-control" id="${b.bookId}" name="${b.bookId}" value="${b.title}"></td>
                                        <td style="text-align:center;">${b.isbn}</td>
                                        <td style="text-align:center;"></td>
                                        <td>

                                            <button type="submit" class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i></button>
                                    </form>
                                </td>
                                <td>
                                    <form class="col-sm-12" method="POST" id="authorForm" action="bookController?action=trydelete&id=${b.bookId}">
                                        <input type="hidden" value="${hiddenId}" name="authorId">
                                        <button type="submit" class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></button>
                                    </form>
                                </td>
                            </tr>

                        </c:forEach>

                        <tr>
                            <td></td>
                            <td>
                                <form class="col-sm-12" method="POST" id="authorForm" action="bookController?action=tryadd">

                                    <div class="form-group">
                                        <label for="bookName">Add Book:</label>

                                        <input type="text" class="form-control" id="bookName" name="bookName">

                                        <input type="hidden" value="${hiddenId}" name="authorId">
                                    </div>
                            </td>

                            <td> 
                                <div class="form-group">
                                    <label for="isbn">ISBN:</label>

                                    <input type="text" class="form-control" id="bookISBN" name="bookISBN">
                                </div>
                            </td>
                            <td> 

                            </td>
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
