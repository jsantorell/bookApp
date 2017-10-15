
package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString;

import java.util.List;

/**
 *
 * @author Jeremy Santorelli
 */
public class Where extends MySQLWhereDecorator {

    private SQLStatementBuilder sql;
    private String columnName;
    private String comparator;
    private String compareValue;

    public Where(SQLStatementBuilder sql, String columnName, String comparator, String compareValue) {
        super(sql, columnName, comparator, compareValue);
        this.sql = sql;
        this.columnName = columnName;
        this.comparator = comparator;
        this.compareValue = compareValue;
    }

    @Override
    public String BuildRetrieveString(String databaseName, String tableName, List<String> columns) {

        String sqlStatement = sql.BuildRetrieveString(databaseName, tableName, columns);
        String wherePart = " WHERE " + columnName + " " + comparator + " " + compareValue;
        return sqlStatement + wherePart;

    }

}
