package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DataAccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MySqlDataAccess implements DataAccess {
    private final int ALL_RECORDS = 0;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    public MySqlDataAccess(String driverClass, 
            String url, String userName, String password) {
        
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        
    }
    
    @Override
    public void openConnection() 
            throws ClassNotFoundException, SQLException {
        
        Class.forName (driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }
    
    @Override
    public void closeConnection() throws SQLException {
        if(conn !=null) conn.close();
    }
    
    /**
     * Returns records from a table. Requires and open connection.
     * @param query
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException 
     * @throws java.lang.ClassNotFoundException 
     */
    @Override
    public List<Map<String,Object>> DatabaseQuery(String query) 
            throws SQLException, ClassNotFoundException {
        
        List<Map<String,Object>> rawData = new Vector<>();
        String sql = query;

        
        openConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String,Object> record = null;
        
        while( rs.next() ) {
            record = new LinkedHashMap<>();
            for(int colNum=1; colNum <= colCount; colNum++) {
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            rawData.add(record);
        }
        
        closeConnection();
        
        return rawData;
    }
    
    
    @Override
    public int InsertUpdateDelete(String query) throws ClassNotFoundException, SQLException{
    
    String sql = query;

        
        openConnection();
        stmt = conn.createStatement();
        int rowsAffected = stmt.executeUpdate(sql);
        closeConnection();
    
        return rowsAffected;
    
    
    }

    @Override
    public String getDriverClass() {
        return driverClass;
    }


    public final void setDriverClass(String driverClass) {
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

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        
//             PutQueryTogether p = PutQueryTogetherForMySql.getInstance();
//        List<String> setOfColumns = new ArrayList<>();
//            
//            setOfColumns.add("author_name");
//            setOfColumns.add("date_added");
// 
//            String query = p.BuildRetrieveString("book", "author", setOfColumns);
//        
//        
//        DataAccess db = new MySqlDataAccess(
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root", ""
//        );
//       
//        List<Map<String,Object>> list = db.DatabaseQuery(query);
//        
//        for(Map<String,Object> rec : list) {
//            System.out.println(rec);
//        }
//        
//    }
    
}
