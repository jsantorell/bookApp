package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
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
    private DataAccess db;

    public AuthorDao(String driverClass, String url,
            String userName, String password,
            DataAccess db) {

        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setDb(db);
    }

    @Override
    public List<Author> getListOfAuthors()
            throws SQLException, ClassNotFoundException {

        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData
                = db.getAllRecords("author", 0);

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

            Date recDate = objRecDate == null ? null : (Date)objRecDate;

            author.setDateAdded(recDate);

            list.add(author);

        }

        return list;
    }
    
    @Override
    public int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException{
    if(id == null || id < 1){
        throw new IllegalArgumentException(" id must be greater than 0");
    }
    return db.deleteRecordById("author", "author_id", id);
    
    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
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
//        AuthorDao adao = new AuthorDao("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/book",
//                "root", "", new MySqlDataAccess("com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root", ""));
//           List<Author> list = adao.getListOfAuthors();
//        
//        for(Author rec : list) {
//            System.out.println(rec.getAuthorId() + ", " + rec.getAuthorName() + ", " + rec.getDateAdded() + "\n");
//        }
//
//    }
    


}
