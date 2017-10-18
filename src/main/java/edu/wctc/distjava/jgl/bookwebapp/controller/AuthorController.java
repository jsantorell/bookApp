package edu.wctc.distjava.jgl.bookwebapp.controller;

import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO.AuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.*;
import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO.IAuthorDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jerem
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authorController"})
public class AuthorController extends HttpServlet {

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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = "authorList.jsp"; // default

        try {
            String action = request.getParameter(ACTION);
            String idFromView = request.getParameter(ID);

            IAuthorDao adao = new AuthorDao(DatabaseSource.MYSQL);

            AuthorService authorService = new AuthorService(adao);

            if (action.equalsIgnoreCase(INDEX_ACTION)) {

                destination = "index.jsp";

            }
            if (action.equalsIgnoreCase(TRY_LOOKUP)) {
                destination = "index.jsp";
                String search = request.getParameter("theSearch");
                List<Author> authorList = authorService.getAuthorSearch(search);
                request.setAttribute("authorList", authorList);
                

            }
            if (action.equalsIgnoreCase(LIST_ACTION)) {

                List<Author> authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);

            }
            if (action.equalsIgnoreCase(TRY_DELETE)) {
                //String id = request.getParameter("AuthorID");

                int rowsAffected = authorService.DeleteAuthor(idFromView);
                List<Author> authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
                request.setAttribute("rowsAffected", rowsAffected + " Record(s) Deleted");
            }

            if (action.equalsIgnoreCase(TRY_ADD)) {
                String aName = request.getParameter("AuthorName");
                int rowsAffected = authorService.AddAuthor(aName);
                request.setAttribute("rowsAffected", rowsAffected + " Record(s) Added");
                List<Author> authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
            }

            if (action.equalsIgnoreCase(TRY_UPDATE)) {

                String aName = request.getParameter(idFromView);
                System.out.println(aName);
                int rowsAffected = 0;

                rowsAffected += authorService.UpdateAuthor(aName, "author_name", idFromView);

                request.setAttribute("rowsAffected", rowsAffected + " Column(s) Affected");
                List<Author> authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
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
