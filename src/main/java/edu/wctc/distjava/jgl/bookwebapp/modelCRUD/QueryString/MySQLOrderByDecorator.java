/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString;

import java.util.List;

/**
 *
 * @author Jeremy Santorelli
 */
public abstract class MySQLOrderByDecorator implements SQLStatementBuilder {

    private SQLStatementBuilder sql;
    private String columnName;
    private String comparator;
   

    public MySQLOrderByDecorator(SQLStatementBuilder sql, String columnName,
            String comparator) {
        this.sql = sql;
        this.columnName = columnName;
        this.comparator = comparator;
     
    }

    @Override
    public String BuildRetrieveString(String databaseName, String tableName, List<String> columns) {

        return sql.BuildRetrieveString(databaseName, tableName, columns);

    }

    /*UNESSASARY To Use These Below. Still needed to have implementation*/
    @Override
    public String BuildCreateString(String databaseName, String tableName, List<String> columns) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String BuildUpdateString(String databaseName, String tableName, String columnName, String idColumnName, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String BuildDeleteString(String databaseName, String tableName, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
