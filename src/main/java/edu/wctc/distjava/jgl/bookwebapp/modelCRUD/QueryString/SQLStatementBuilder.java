package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jeremy Santorelli
 */
public interface SQLStatementBuilder {
    
    
    public String BuildCreateString(String databaseName, String tableName, List<String> columns);
    public String BuildRetrieveString(String databaseName, String tableName, List<String> columns);
    public String BuildUpdateString(String databaseName, String tableName, String columnName, String idColumnName, String id);
    public String BuildDeleteString(String databaseName, String tableName, String id);
    

}
