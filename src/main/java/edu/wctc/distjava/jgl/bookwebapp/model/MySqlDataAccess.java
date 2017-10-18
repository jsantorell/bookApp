package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

public class MySqlDataAccess implements DataAccess {

    private final int ALL_RECORDS = 0;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    PreparedStatement ps;

    @Override
    public void openConnection(String driverClass,
            String url, String userName, String password)
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
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Map<String, Object>> getAllRecords(String tableName, int maxRecords)
            throws SQLException, ClassNotFoundException {

        List<Map<String, Object>> rawData = new Vector<>();
        String sql = "";

        if (maxRecords > ALL_RECORDS) {
            sql = "select * from " + tableName + " limit " + maxRecords;
        } else {
            sql = "select * from " + tableName;
        }

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

        return rawData;
    }

    @Override
    public int deleteRecordById(String tableName, String pkColName, Object pk) throws ClassNotFoundException, SQLException {

        String sql = "DELETE FROM " + tableName + " WHERE " + pkColName + " = ?";

        this.ps = conn.prepareStatement(sql);
        ps.setObject(1, pk);
        int recsDeleted = ps.executeUpdate();

        return recsDeleted;

    }

    @Override
    public int CreateRecord(String tableName, List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(",", "(", ")");
        for (String columns : colNames) {
            sj.add(columns);
        }
        sql += sj.toString();
        sql += " VALUES ";
        sj = new StringJoiner(",", "(", ")");
        for (Object Value : colValues) {

            sj.add("?");
        }
        sql += sj.toString();

        System.out.println(sql);

        this.ps = conn.prepareStatement(sql);

        for (int item = 0; item < colValues.size(); item++) {

            ps.setObject((item + 1), colValues.get(item));

        }

        int recordsAffected = ps.executeUpdate();

        return recordsAffected;
    }

    public int updateRecord(String tableName, List colNames, List colValues, String pkColName, Object pkValue) throws SQLException {

        String sql = "UPDATE " + tableName + " SET ";

        for (int i = 0; i < colNames.size(); i++) {

            if (i < colNames.size() - 1) {

                sql += colNames.get(i) + " = " + "?, ";

            } else {

                sql += colNames.get(i) + " = " + "?";

            }

        }

        sql += " WHERE " + pkColName + " = ?;";

        // System.out.println(sql);
        ps = conn.prepareStatement(sql);

        for (int i = 1; i <= colValues.size(); i++) {

            ps.setObject(i, colValues.get(i - 1));

        }

        ps.setObject(colValues.size() + 1, pkValue);
        
        return ps.executeUpdate();

    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//
//        DataAccess db = new MySqlDataAccess();
//
//        db.openConnection("com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root", "");
//
//        int recs = db.CreateRecord("author", Arrays.asList("author_name", "date_added"), Arrays.asList("author_name", "2017-07-07"));
//
//        db.closeConnection();
//        System.out.println("Records Updated = " + recs);
//
////Get All Records.
////        db.openConnection("com.mysql.jdbc.Driver",
////                "jdbc:mysql://localhost:3306/book",
////                "root", "");
////        int recs = db.deleteRecordById("author", "author_id", 14);
////        List<Map<String, Object>> list = db.getAllRecords("author", 0);
////        System.out.println(recs);
////        list.forEach((rec) -> {
////            System.out.println(rec);
////        });
////        db.closeConnection();
//    }

}
