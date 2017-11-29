package edu.wctc.distjava.jgl.bookwebapp.controller;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.model.Book1;
import edu.wctc.distjava.jgl.bookwebapp.model.BookFacade;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @author Jeremy Santorelli
 *
 *
 */
@WebServlet(name = "BookController", urlPatterns = {"/bookController"})
public class BookController extends HttpServlet {

    @EJB
    BookFacade bookFace;

    public static final String ACTION = "action";
    public static final String ID = "id";
    public static final String LIST_ACTION = "list";
    public static final String INDEX_ACTION = "index";

    private static final String TRY_ADD = "tryadd";
    private static final String TRY_UPDATE = "tryupdate";
    private static final String TRY_DELETE = "trydelete";
    private static final String TRY_LOOKUP = "trylookup";
    private static final long serialVersionUID = 1L;

    String driverClass;
    String url;
    String userName;
    String password;

    /**
     *
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = "bookList.jsp"; // default

        try {
            String action = request.getParameter(ACTION);
            String idFromView = request.getParameter(ID);

            if (action.equalsIgnoreCase(INDEX_ACTION)) {

                destination = "index.jsp";
                List<Book1> books = bookFace.findAll();
                request.setAttribute("authorList", books);
                

            }
            if (action.equalsIgnoreCase(TRY_LOOKUP)) {
                destination = "index.jsp";
                String search = request.getParameter("theSearch");
//                List<Author> bookList = bookFace.find(search);
//                request.setAttribute("authorList", bookList);

            }
            if (action.equalsIgnoreCase(LIST_ACTION)) {

                int authorId = Integer.parseInt(idFromView);
                Author author = bookFace.getAuthorFromBook(authorId);
                request.setAttribute("author", author.getAuthorName());
                request.setAttribute("hiddenId", idFromView);
                List<Book1> bookList = bookFace.findAllByAuthorId(authorId);
                request.setAttribute("bookList", bookList);

            }
            if (action.equalsIgnoreCase(TRY_DELETE)) {
                String authorId = request.getParameter("authorId");
                request.setAttribute("hiddenId", authorId);
                int authorIdToInt = Integer.parseInt(authorId);
                Book1 book = bookFace.find(Integer.parseInt(idFromView));
                String authorName = book.getAuthorId().getAuthorName();
                request.setAttribute("author", authorName);
                request.setAttribute("rowsAffected", book.getTitle() + " removed");
                bookFace.remove(book);
                List<Book1> bookList = bookFace.findAllByAuthorId(authorIdToInt);
                request.setAttribute("bookList", bookList);

            }

            if (action.equalsIgnoreCase(TRY_ADD)) {
                String bName = request.getParameter("bookName");
                String isbn = request.getParameter("bookISBN");
                String authorId = request.getParameter("authorId");
                request.setAttribute("hiddenId", authorId);
                int authorIdToInt = Integer.parseInt(authorId);
                Author author = bookFace.getAuthorFromBook(authorIdToInt);
                request.setAttribute("author", author.getAuthorName());
                Book1 book = new Book1();
                book.setTitle(bName);
                book.setIsbn(isbn);
                book.setAuthorId(author);
                bookFace.create(book);
                request.setAttribute("rowsAffected", book.getTitle() + " Added");
                List<Book1> bookList = bookFace.findAllByAuthorId(author.getAuthorId());
                request.setAttribute("bookList", bookList);
            }

            if (action.equalsIgnoreCase(TRY_UPDATE)) {
                String aName = request.getParameter(idFromView);

                Book1 book = bookFace.find(Integer.parseInt(idFromView));
                String oldName = book.getTitle();
                int authorId = book.getAuthorId().getAuthorId();
                request.setAttribute("hiddenId", authorId);
                book.setTitle(aName);
                bookFace.edit(book);
                request.setAttribute("author", book.getAuthorId().getAuthorName());
                request.setAttribute("rowsAffected", oldName + " Changed To " + book.getTitle());
                List<Book1> bookList = bookFace.findAllByAuthorId(book.getAuthorId().getAuthorId());
                request.setAttribute("bookList", bookList);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

            request.setAttribute("rowsAffected", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }

    //PASS THIS TO THE PROPER LEVEL LATER
    @Override
    public void init() throws ServletException {
        driverClass = getServletContext()
                .getInitParameter("db.driver.class");
        url = getServletContext()
                .getInitParameter("db.url");
        userName = getServletContext()
                .getInitParameter("db.username");
        password = getServletContext()
                .getInitParameter("db.password");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
