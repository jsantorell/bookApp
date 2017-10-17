package edu.wctc.distjava.jgl.bookwebapp.modelCRUD;

import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.MySQLStatementBuilder;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO.IAuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO.AuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.OrderBy;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.SQLComparisonType;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.SQLStatementBuilder;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.Where;

/**
 *
 * @author jerem
 */
public final class AuthorService {

    private IAuthorDao authorDAO;
    private SQLStatementBuilder p = MySQLStatementBuilder.getInstance();
    private String dbName = "book";
    private String tbName = "author";

    public AuthorService(IAuthorDao authorDAO) {
        setAuthorDAO(authorDAO);
    }

    public IAuthorDao getAuthorDAO() {
        return authorDAO;
    }

    public void setAuthorDAO(IAuthorDao authorDAO) {
        this.authorDAO = authorDAO;
    }

    public List<Author> getAuthorSearch(String val) throws ClassNotFoundException, SQLException {

        List<String> setOfColumns = authorDAO.getStringOfCols();
        setOfColumns.add("author_id");
        String colName = "author_name";
        p = new OrderBy(new Where(p, colName, SQLComparisonType.LIKE, "'%" + val + "%'"), "author_id", "");
        String query = p.BuildRetrieveString(this.dbName, this.tbName, setOfColumns);
        //System.out.println(query);

        return authorDAO.getListOfAuthors(query);

    }
    //Not currently being utilized. Built for future use.
    public List<Author> getAuthorByID(String val) throws ClassNotFoundException, SQLException {
        String colName = "author_id";
        List<String> setOfColumns = authorDAO.getStringOfCols();
        setOfColumns.add(colName);
        //Put the object together
        p = new OrderBy(new Where(p, colName, SQLComparisonType.EQUALS, val), colName, "");
        //Build the statement
        String query = p.BuildRetrieveString(this.dbName, this.tbName, setOfColumns);
        //System.out.println(query);

        return authorDAO.getListOfAuthors(query);

    }

    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {

        List<String> setOfColumns = authorDAO.getStringOfCols();
        setOfColumns.add("author_id");
        //Make sure the object is ordered
        p = new OrderBy(p, "author_id", "");
        //build the statement
        String query = p.BuildRetrieveString(this.dbName, this.tbName, setOfColumns);
        //System.out.println(query);

        return authorDAO.getListOfAuthors(query);

    }

    public int AddAuthor(String name) throws SQLException, ClassNotFoundException {
        List<String> setOfColumns = authorDAO.getStringOfCols();
        List<List<String>> dataSets = new ArrayList<>();
        List<String> data = new ArrayList<>();
        data.add(name);
        data.add(getFormattedDateNow());
        dataSets.add(data);

        String query = p.BuildCreateString(this.dbName, this.tbName, setOfColumns);

        return authorDAO.insertAuthor(query, dataSets);
    }

    public int DeleteAuthor(String id) throws SQLException, ClassNotFoundException {

        String query = p.BuildDeleteString(this.dbName, this.tbName, id);

        return authorDAO.deleteAuthor(query);
    }

    public int UpdateAuthor(String data, String columnName, String id) throws SQLException, ClassNotFoundException {

        String columnIDName = "author_id";

        String query = p.BuildUpdateString(this.dbName, this.tbName, columnName, columnIDName, id);
        //System.out.println(query);
        return authorDAO.updateAuthor(query, data);
    }

    public String getFormattedDateNow() {//Put this elsewhere in the future

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());

    }

//    Integration Service
//    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        IAuthorDao a = new AuthorDao(DatabaseSource.MYSQL);
//        AuthorService as = new AuthorService(a);
//        List<String> setOfColumns = a.getStringOfCols();
//        setOfColumns.add("author_id");
//
//        String query = MySQLStatementBuilder.getInstance().BuildRetrieveString("book", "author", setOfColumns);
//    
//        System.out.println(query);
//
//        List<Author> list = as.getAuthorList();
//
//        for (Author rec : list) {
//            System.out.println(rec.getAuthorId() + ", " + rec.getAuthorName() + ", " + rec.getDateAdded() + "\n");
//        }
////        System.out.println("" + as.UpdateAuthor());
////         System.out.println("" + as.AddAuthor());
////        System.out.println("" + as.DeleteAuthor());
//
//    }
}
