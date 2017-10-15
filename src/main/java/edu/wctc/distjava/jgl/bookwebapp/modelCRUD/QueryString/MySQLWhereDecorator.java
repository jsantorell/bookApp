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
public abstract class MySQLWhereDecorator implements SQLStatementBuilder {

    private SQLStatementBuilder sql;
    private String columnName;
    private String comparator;
    private String compareValue;

    public MySQLWhereDecorator(SQLStatementBuilder sql, String columnName,
            String comparator, String compareValue) {
        this.sql = sql;
        this.columnName = columnName;
        this.comparator = comparator;
        this.compareValue = compareValue;
    }

    @Override
    public String BuildRetrieveString(String databaseName, String tableName, List<String> columns) {

        return sql.BuildRetrieveString(databaseName, tableName, columns);

    }

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
