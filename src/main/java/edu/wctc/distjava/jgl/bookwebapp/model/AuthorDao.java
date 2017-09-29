package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author jlombardo
 */
public class AuthorDao {
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
    
    public List<Author> getListOfAuthors() 
            throws SQLException, ClassNotFoundException {
        
        List<Author> list = new Vector<>();
        List<Map<String,Object>> rawData = 
                db.getAllRecords("author", 0);
        
        Author author = null;
        
        for(Map<String,Object> rec : rawData) {
              author = new Author(); 
              author.setAuthorId(
                      Integer.parseInt(
                              rec.get("author_id")
                                      .toString()));
              author.setAuthorName(rec.get("author_name").toString());
              author.setDateAdded((Date)rec.get("date_added"));
              
              list.add(author);
              
        }
          
        return list;
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
    
    public static void main(String[] args) {
        
    }
    
}
