package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DataAccess;

import edu.wctc.distjava.jgl.bookwebapp.model.config.ConfigurationParser;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.MySQLStatementBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString.SQLStatementBuilder;
import java.util.Properties;

public final class MySqlDataAccess implements DataAccess {

    private final int ALL_RECORDS = 0;

    private ConfigurationParser c = ConfigurationParser.getInstance();
    private Properties properties;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private PreparedStatement ps;

    public MySqlDataAccess() throws Exception {

        properties = c.getConfigutationProperties();
        this.driverClass = properties.getProperty("driver.class");
        this.url = properties.getProperty("sql.db");
        this.userName = properties.getProperty("u");
        this.password = properties.getProperty("p");
    }

    @Override
    public void openConnection()
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * Returns records from a table. Requires and open connection.
     *
     * @param query
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Map<String, Object>> DatabaseQuery(String query)
            throws SQLException, ClassNotFoundException {

        List<Map<String, Object>> rawData = new Vector<>();
        String sql = query;

        openConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            rawData.add(record);
        }

        closeConnection();

        return rawData;
    }


    @Override
    public int InsertRecord(String query, List<List<String>> dataSets) throws SQLException, ClassNotFoundException {
        String sql = query;
        int rowsAffected = 0;

        openConnection();

        for (int rowOfData = 0; rowOfData < dataSets.size(); rowOfData++) {
            this.ps = conn.prepareStatement(sql);
            for (int cell = 0; cell < dataSets.get(rowOfData).size(); cell++) {

                ps.setObject((cell + 1), dataSets.get(rowOfData).get(cell));

            }

            rowsAffected = ps.executeUpdate();
        }

        closeConnection();

        return rowsAffected;
    }

    @Override
    public int UpdateRecord(String query, Object value) throws SQLException, ClassNotFoundException {

        openConnection();
        this.ps = conn.prepareStatement(query);
        ps.setObject(1, value);
        int rowsAffected = ps.executeUpdate();
        closeConnection();

        return rowsAffected;

    }

    @Override
    public int DeleteRecord(String query) throws SQLException, ClassNotFoundException {
      openConnection();
      stmt = conn.createStatement();
       int a = stmt.executeUpdate(query);
        closeConnection();
        return a;
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception {
//
//        SQLStatementBuilder p = MySQLStatementBuilder.getInstance();
//        List<String> setOfColumns = new ArrayList<>();
//
//        setOfColumns.add("author_name");
//        setOfColumns.add("date_added");
//
//        String query = p.BuildUpdateString("book", "author", "author_name", "author_id", "14");
//        System.out.println(query);
//        DataAccess db = new MySqlDataAccess();
//
//        List<List<String>> dataSets = new ArrayList<>();
//
//        List<String> a = new ArrayList<>();
//        List<String> b = new ArrayList<>();
//
//        a.add("John Smith");
//        a.add("2017-01-01");
//
//        b.add("Rab Dab");
//        b.add("2011-11-11");
//
//        dataSets.add(a);
//        dataSets.add(b);
//
//        int list = db.UpdateRecord(query, "barry manaloe");
//
//        System.out.println(list);
//
////        for(Map<String,Object> rec : list) {
////            System.out.println(rec);
////        }
//    }

}
