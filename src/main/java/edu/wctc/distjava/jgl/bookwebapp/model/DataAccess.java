package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jerem
 */
public interface DataAccess {

    void closeConnection() throws SQLException;

    /**
     * Returns records from a table. Requires and open connection.
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) throws SQLException, ClassNotFoundException;
public int deleteRecordById(String tableName, String pkColName, Object pk) throws ClassNotFoundException, SQLException;

    void openConnection(String driverClass,
            String url, String userName, String password) throws ClassNotFoundException, SQLException;

    public int CreateRecord(String tableName, List<String> colNames, List<Object> colValues)throws ClassNotFoundException, SQLException ;
    public int updateRecord(String tableName, List colNames, List colValues, String pkColName, Object pkValue) throws SQLException;
}
