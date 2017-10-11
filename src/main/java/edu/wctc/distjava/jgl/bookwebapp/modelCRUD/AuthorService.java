package edu.wctc.distjava.jgl.bookwebapp.modelCRUD;

import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.MySQLStatementBuilder;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO.IAuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO.AuthorDao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.SQLStatementBuilder;

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

    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {

        List<String> setOfColumns = authorDAO.getStringOfCols();
        setOfColumns.add("author_id");

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
        //Once a UI is created i will add the inputs in as these values.
        
        
        String columnIDName = "author_id";

        String query = p.BuildUpdateString(this.dbName, this.tbName, columnName, columnIDName, id);

        return authorDAO.updateAuthor(query, data);
    }

    public String getFormattedDateNow() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());

    }

//    Integration Service
//    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        AuthorService as = new AuthorService(new AuthorDao(DatabaseSource.MYSQL));
//
//        List<Author> list = as.getAuthorList();
//
//        for (Author rec : list) {
//            System.out.println(rec.getAuthorId() + ", " + rec.getAuthorName() + ", " + rec.getDateAdded() + "\n");
//        }
//        //System.out.println("" + as.UpdateAuthor());
//         //System.out.println("" + as.AddAuthor());
//        //System.out.println("" + as.DeleteAuthor());
//
//    }
}
