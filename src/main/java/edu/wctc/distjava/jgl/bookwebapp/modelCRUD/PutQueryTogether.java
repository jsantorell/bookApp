package edu.wctc.distjava.jgl.bookwebapp.modelCRUD;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jeremy Santorelli
 */
public interface PutQueryTogether {
    
    
    public String BuildCreateString(String databaseName, String tableName, List<String> columns, List<List<String>> dataSets);
    public String BuildRetrieveString(String databaseName, String tableName, List<String> columns);
    public String BuildUpdateString(String databaseName, String tableName, String columnName, String Value, String idColumnName, int id);
    public String BuildDeleteString(String databaseName, String tableName, int id);
    

}
