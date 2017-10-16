package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString;

import java.util.List;

/**
 *
 * @author Jeremy Santorelli
 */
public class OrderBy extends MySQLOrderByDecorator {

    private SQLStatementBuilder sql;
    private String columnName;
    private String comparator;

    public OrderBy(SQLStatementBuilder sql, String columnName, String comparator) {
        super(sql, columnName, comparator);
        this.sql = sql;
        this.columnName = columnName;
        this.comparator = comparator;
    }

    @Override
    public String BuildRetrieveString(String databaseName, String tableName, List<String> columns) {

        String sqlStatement = sql.BuildRetrieveString(databaseName, tableName, columns);
        String wherePart = " ORDER BY " + columnName + " " + comparator;
        return sqlStatement + wherePart;

    }

}
