package edu.wctc.distjava.jgl.bookwebapp.controller;

import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO.AuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.*;
import edu.wctc.distjava.jgl.bookwebapp.model.Author;
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
    public static final String LIST_ACTION = "list";
    public static final String ADD_ACTION = "add";
    public static final String UPDATE_ACTION = "update";

    private static final String TRY_ADD = "tryadd";
    private static final String TRY_UPDATE = "tryupdate";

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

        String destination = "error.jsp"; // default

        try {
            String action = request.getParameter(ACTION);

            String driver = "com.mysql.jdbc.Driver";
            String database = "jdbc:mysql://localhost:3306/book";
            String userName = "root";
            String password = "";

            AuthorService authorService = new AuthorService(new AuthorDao(driver, database,
                    userName, password, DatabaseSource.MYSQL));

            if (action.equalsIgnoreCase(LIST_ACTION)) {
                
                List<Author> authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
                destination = "authorList.jsp";
            }
            if (action.equalsIgnoreCase(ADD_ACTION)) {
                destination = "add.jsp";
            }
            if (action.equalsIgnoreCase(TRY_ADD)) {
                String aName = request.getParameter("AuthorName");
                int rowsAffected = authorService.AddAuthor(aName);
                request.setAttribute("rowsAffected", rowsAffected + " Record(s) Added");
                destination = "add.jsp";
            }
            if(action.equalsIgnoreCase(UPDATE_ACTION)){
            
            destination = "update.jsp";
            }
            if (action.equalsIgnoreCase(TRY_UPDATE)) {//Still Working On this ... 
                //... Currently not Working
                String aName = request.getParameter("AuthorName");
                String date = request.getParameter("DateAdded");
                int id = Integer.parseInt(request.getParameter("AuthorID").trim());
                int rowsAffected = 0;
                if (date.length() > 9) {

                    rowsAffected += authorService.UpdateAuthor(date, "date_added", id);

                }
                if (aName.length() > 0) {

                    rowsAffected += authorService.UpdateAuthor(aName, "author_name", id);

                }

                request.setAttribute("rowsAffected", rowsAffected + " Column(s) Affected");
                destination = "update.jsp";
            }

        } catch (Exception e) {
            destination = "/error.jsp";
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);

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
