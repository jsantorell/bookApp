package edu.wctc.distjava.jrs.bookwebapp.controller;

import edu.wctc.distjava.jrs.bookwebapp.model.Author;
import edu.wctc.distjava.jrs.bookwebapp.model.AuthorFacade;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 *
 * @author Jeremy Santorelli
 *
 *
 */
@Controller
@WebServlet(name = "AuthorController", urlPatterns = {"/authorController"})
public class AuthorController extends HttpServlet {

    AuthorFacade authorFace;

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

        String destination = "authorList.jsp"; // default

        try {
            String action = request.getParameter(ACTION);
            String idFromView = request.getParameter(ID);

            if (action.equalsIgnoreCase(INDEX_ACTION)) {

                destination = "index.jsp";

            }
            if (action.equalsIgnoreCase(TRY_LOOKUP)) {
                destination = "index.jsp";
                String search = request.getParameter("theSearch");
                //List<Author> authorList = authorService.getAuthorSearch(search);
                //request.setAttribute("authorList", authorList);

            }
            if (action.equalsIgnoreCase(LIST_ACTION)) {

                List<Author> authorList = authorFace.findAll();
                request.setAttribute("authorList", authorList);

            }
            if (action.equalsIgnoreCase(TRY_DELETE)) {
                //String id = request.getParameter("AuthorID");
                Author author = authorFace.findById(idFromView);
                authorFace.remove(author);
                List<Author> authorList = authorFace.findAll();
                request.setAttribute("authorList", authorList);
                request.setAttribute("rowsAffected", author.getAuthorName() + " Removed");
            }

            if (action.equalsIgnoreCase(TRY_ADD)) {
                String aName = request.getParameter("AuthorName");
                authorFace.createAuthor(aName);
                request.setAttribute("rowsAffected", "Record Added");
                List<Author> authorList = authorFace.findAll();
                request.setAttribute("authorList", authorList);
            }

            if (action.equalsIgnoreCase(TRY_UPDATE)) {
                String aName = request.getParameter(idFromView);
                Author author = authorFace.findById(idFromView);
                String oldName = author.getAuthorName();
                author.setAuthorName(aName);
                authorFace.edit(author);

                request.setAttribute("rowsAffected", oldName + " Changed To " + author.getAuthorName());
                List<Author> authorList = authorFace.findAll();
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

//    //PASS THIS TO THE PROPER LEVEL LATER
//    @Override
//    public void init() throws ServletException {
//        driverClass = getServletContext()
//                .getInitParameter("db.driver.class");
//        url = getServletContext()
//                .getInitParameter("db.url");
//        userName = getServletContext()
//                .getInitParameter("db.username");
//        password = getServletContext()
//                .getInitParameter("db.password");
//    }
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
    }

    @Override
    public void init() throws ServletException {

        ServletContext sctx = getServletContext();

        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);

        authorFace = (AuthorFacade) ctx.getBean("authorFacade");

    }

// </editor-fold>
}
