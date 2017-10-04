package edu.wctc.distjava.jgl.bookwebapp.modelCRUD;

import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.PutQueryTogetherForMySql;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.PutQueryTogether;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO.IAuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jerem
 */
public final class AuthorService {

    private IAuthorDao authorDAO;
    private PutQueryTogether p = PutQueryTogetherForMySql.getInstance();
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

        List<String> setOfColumns = new ArrayList<>();
        setOfColumns.add("author_id");
        setOfColumns.add("author_name");
        setOfColumns.add("date_added");

        String query = p.BuildRetrieveString(this.dbName, this.tbName, setOfColumns);
        System.out.println(query);

        return authorDAO.getListOfAuthors(query);

    }

    public int AddAuthor(String name) throws SQLException, ClassNotFoundException {
        List<String> setOfColumns = new ArrayList<>();

        setOfColumns.add("author_name");
        setOfColumns.add("date_added");
        List<List<String>> dataSets = new ArrayList<>();
        List<String> data = new ArrayList<>();
        data.add(name);
        data.add(getFormattedDateNow());
        dataSets.add(data);

        String query = p.BuildCreateString(this.dbName, this.tbName, setOfColumns, dataSets);

        return authorDAO.getRowsAffected(query);
    }

    public int DeleteAuthor(String id) throws SQLException, ClassNotFoundException {

      

        String query = p.BuildDeleteString(this.dbName, this.tbName, id);

        return authorDAO.getRowsAffected(query);
    }

    public int UpdateAuthor(String data, String columnName, String id) throws SQLException, ClassNotFoundException {
        //Once a UI is created i will add the inputs in as these values.
        
        
        String columnIDName = "author_id";
        String value = data;

        String query = p.BuildUpdateString(this.dbName, this.tbName, columnName, value, columnIDName, id);

        return authorDAO.getRowsAffected(query);
    }

    public String getFormattedDateNow() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());

    }

//    Integration Service
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        AuthorService as = new AuthorService(new AuthorDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book",
//                "root", "", DatabaseSource.MYSQL));
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
