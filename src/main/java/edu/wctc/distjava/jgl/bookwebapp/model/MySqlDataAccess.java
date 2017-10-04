package edu.wctc.distjava.jgl.bookwebapp.model;

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
    
    public void openConnection() 
            throws ClassNotFoundException, SQLException {
        
        Class.forName (driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }
    
    public void closeConnection() throws SQLException {
        if(conn !=null) conn.close();
    }
    
    /**
     * Returns records from a table. Requires and open connection.
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException 
     */
    public List<Map<String,Object>> getAllRecords(String tableName, int maxRecords) 
            throws SQLException, ClassNotFoundException {
        
        List<Map<String,Object>> rawData = new Vector<>();
        String sql = "";
        
        if(maxRecords > ALL_RECORDS) {
            sql = "select * from " + tableName + " limit " + maxRecords;
        } else {
            sql = "select * from " + tableName;
        }
        
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
    
    
    public int deleteRecordById(String tableName, String pkColName, Object pk) throws ClassNotFoundException, SQLException{
    
    
        String sql = "DELETE FROM " + tableName + " WHERE " + pkColName + " = ";
    
        if(pk instanceof String){
        
            sql += "'" + pk.toString() + "'";
        }
        
        else {
        
            sql +=  Long.parseLong(pk.toString());
        }
        
        
        openConnection();
        stmt = conn.createStatement();
        int recsDeleted = stmt.executeUpdate(sql);
        closeConnection();
        return recsDeleted;
        
    }

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
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        DataAccess db = new MySqlDataAccess(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", ""
        );
        int recs = db.deleteRecordById("author", "author_id", 1);
        List<Map<String,Object>> list = db.getAllRecords("author", 0);
        System.out.println(recs);
        for(Map<String,Object> rec : list) {
            System.out.println(rec);
        }
        
    }
    
}
