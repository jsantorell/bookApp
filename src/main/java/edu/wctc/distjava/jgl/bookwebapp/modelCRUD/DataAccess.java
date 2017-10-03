package edu.wctc.distjava.jgl.bookwebapp.modelCRUD;

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
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    List<Map<String, Object>> DatabaseQuery(String tableName) throws SQLException, ClassNotFoundException;
    int InsertUpdateDelete(String tableName) throws SQLException, ClassNotFoundException;

    String getDriverClass();

    String getPassword();

    String getUrl();

    String getUserName();

    void openConnection() throws ClassNotFoundException, SQLException;

    void setDriverClass(String driverClass);

    void setPassword(String password);

    void setUrl(String url);

    void setUserName(String userName);
    
}
