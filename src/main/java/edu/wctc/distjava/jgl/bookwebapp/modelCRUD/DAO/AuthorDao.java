package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DataAccess.DataAccess;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DatabaseSource;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DataAccess.MsSqlServerDataAccess;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DataAccess.MySqlDataAccess;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author jerem
 */
public final class AuthorDao implements IAuthorDao {

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

    
    
    public AuthorDao(String driverClass, String url,
            String userName, String password,
            DatabaseSource db) {

        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setdSource(db);
        setDb(this.dSource);
        this.stringOfCols.add("author_name");
        this.stringOfCols.add("date_added");
    }

    @Override
    public List<Author> getListOfAuthors(String query)
            throws SQLException, ClassNotFoundException {

        

        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData = db.DatabaseQuery(query);

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
    public int getRowsAffected(String query) throws SQLException, ClassNotFoundException{
    
    return db.InsertUpdateDelete(query);
    
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

    public void setDb(DatabaseSource ds) {

        switch (ds) {

            case MYSQL:
                this.db = new MySqlDataAccess(this.driverClass, this.url, this.userName, this.password);
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
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        
//        PutQueryTogether p = PutQueryTogetherForMySql.getInstance();
//        List<String> setOfColumns = new ArrayList<>();
//        setOfColumns.add("author_id");
//        setOfColumns.add("author_name");
//        setOfColumns.add("date_added");
//
//        String query = p.BuildRetrieveString("book", "author", setOfColumns);
//        
//        IAuthorDao adao = new AuthorDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book",
//                "root", "", DatabaseSource.MYSQL);
//
//        List<Author> list = adao.getListOfAuthors(query);
//
//        for (Author rec : list) {
//            System.out.println(rec.getAuthorId() + ", " + rec.getAuthorName() + ", " + rec.getDateAdded() + "\n");
//        }
//
//    }

}
