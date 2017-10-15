package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DataAccess;

import edu.wctc.distjava.jgl.bookwebapp.model.*;
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
     *
     * @param tableName
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    List<Map<String, Object>> DatabaseQuery(String tableName) throws SQLException, ClassNotFoundException;

    int InsertRecord(String query, List<List<String>> dataSets) throws SQLException, ClassNotFoundException;

    int UpdateRecord(String query, Object dataSet) throws SQLException, ClassNotFoundException;

    int DeleteRecord(String query) throws SQLException, ClassNotFoundException;

    void openConnection() throws ClassNotFoundException, SQLException;


}
