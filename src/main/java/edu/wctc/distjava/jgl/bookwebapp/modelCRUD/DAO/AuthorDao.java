package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.model.config.ConfigurationParser;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DataAccess.DataAccess;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DatabaseSource;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DataAccess.MsSqlServerDataAccess;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DataAccess.MySqlDataAccess;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.MySQLStatementBuilder;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.SQLStatementBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author jerem
 */
public final class AuthorDao implements IAuthorDao {

    private ConfigurationParser c = ConfigurationParser.getInstance();
    private Properties properties;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DatabaseSource dSource;
    private DataAccess db;
    private List<String> stringOfCols = new ArrayList<>();

    @Override
    public List<String> getStringOfCols() {
        return stringOfCols;
    }

    public void setStringOfCols(List<String> stringOfCols) {
        this.stringOfCols = stringOfCols;
    }

    public AuthorDao(DatabaseSource dSource) throws Exception {
        properties = c.getConfigutationProperties();
        this.driverClass = properties.getProperty("driver.class");
        this.url = properties.getProperty("sql.db");
        this.userName = properties.getProperty("u");
        this.password = properties.getProperty("p");
        setdSource(dSource);
        setDb(this.dSource);
        this.stringOfCols.add("author_name");
        this.stringOfCols.add("date_added");
    }

    @Override
    public List<Author> getListOfAuthors(String query)
            throws SQLException, ClassNotFoundException {
        db.openConnection();
        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData = db.DatabaseQuery(query);
        db.closeConnection();
        Author author = null;

        for (Map<String, Object> rec : rawData) {
            author = new Author();

            Object objRecID = rec.get("author_id");

            Integer recId = objRecID == null ? 0 : Integer.parseInt(objRecID.toString());

            author.setAuthorId(
                    Integer.parseInt(
                            rec.get("author_id")
                                    .toString()));

            Object objRecName = rec.get("author_name");

            String recName = objRecID == null ? "" : objRecName.toString();

            author.setAuthorName(recName);

            Object objRecDate = rec.get("date_added");

            Date recDate = objRecDate == null ? null : (Date) objRecDate;

            author.setDateAdded(recDate);

            list.add(author);

        }

        return list;
    }

    @Override
    public int insertAuthor(String query, List<List<String>> dataSets) throws SQLException, ClassNotFoundException {
        db.openConnection();
        int recordsInserted = db.InsertRecord(query, dataSets);
        db.closeConnection();
        return recordsInserted;
    }

    @Override
    public int updateAuthor(String query, Object value) throws SQLException, ClassNotFoundException {
        db.openConnection();
        int recordsUpdated = db.UpdateRecord(query, value);
        db.closeConnection();
        return recordsUpdated;
    }

    @Override
    public int deleteAuthor(String query) throws SQLException, ClassNotFoundException {
        db.openConnection();
        int recordsDeleted = db.DeleteRecord(query);
        db.closeConnection();
        return recordsDeleted;
    }

    public DatabaseSource getdSource() {
        return dSource;
    }

    public void setdSource(DatabaseSource dSource) {
        this.dSource = dSource;
    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DatabaseSource ds) throws Exception {

        switch (ds) {

            case MYSQL:
                this.db = new MySqlDataAccess();
                break;
            case MICROSOFT:
                this.db = new MsSqlServerDataAccess(this.driverClass, this.url, this.userName, this.password);
                break;
            default:
                this.db = null;

        }
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //INTEGRATION TESTING
//    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception {
//
//        SQLStatementBuilder p = MySQLStatementBuilder.getInstance();
//        List<String> setOfColumns = new ArrayList<>();
//        IAuthorDao adao = new AuthorDao(DatabaseSource.MYSQL);
//        String a = p.BuildUpdateString("book", "author", "author_name", "author_id", "21");
//        int b = adao.updateAuthor(a, "bob");
//        System.out.println(b);
////        setOfColumns.add("author_id");
////        setOfColumns.add("author_name");
////        setOfColumns.add("date_added");
////
////        String query = p.BuildRetrieveString("book", "author", setOfColumns);
////
////        IAuthorDao adao = new AuthorDao(DatabaseSource.MYSQL);
////
////        List<Author> list = adao.getListOfAuthors(query);
////
////        for (Author rec : list) {
////            System.out.println(rec.getAuthorId() + ", " + rec.getAuthorName() + ", " + rec.getDateAdded() + "\n");
////        }
//
//    }
}
