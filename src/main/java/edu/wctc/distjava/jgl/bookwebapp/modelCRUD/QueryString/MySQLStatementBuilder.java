package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jeremy Santorelli
 */
public class MySQLStatementBuilder implements SQLStatementBuilder {

    private static MySQLStatementBuilder query;

    private MySQLStatementBuilder() {
    }

    /**
     *
     * @return a ParkingFactory and ensures only one was made.
     */
    public static MySQLStatementBuilder getInstance() {

        if (MySQLStatementBuilder.query == null) {
            MySQLStatementBuilder.query = new MySQLStatementBuilder();

        }

        return MySQLStatementBuilder.query;

    }

    @Override
    public String BuildCreateString(String databaseName, String tableName, List<String> columns) {

        String columnsSet = "";
        String queryString = "";
        String values = "VALUES ( ";

        for (int i = 0; i < columns.size(); i++) {

            if (i == 0) {

                columnsSet += "`" + columns.get(i) + "`";
                values += "? ";

            } else {

                columnsSet += ",`" + columns.get(i) + "`";
                values += ", ? ";
            }

        }

        String c = "INSERT INTO `" + databaseName + "`.`" + tableName + "` (" + columnsSet + ")";


            queryString += c + values + ");";
        

        return queryString;
    }

    @Override
    public String BuildRetrieveString(String databaseName, String tableName, List<String> columns) {

        String columnsSet = "";

        for (int i = 0; i < columns.size(); i++) {

            if (columns.get(i).equals("*")) {

                columnsSet += "*";

            } else if (i == 0) {

                columnsSet += "`" + columns.get(i) + "`";

            } else {

                columnsSet += ",`" + columns.get(i) + "`";
            }

        }

        return "SELECT " + columnsSet + " FROM " + databaseName + "." + tableName;

    }

    @Override
    public String BuildUpdateString(String databaseName, String tableName,
            String columnName, String idColumnName, String id) {
   
        return "UPDATE `" + databaseName + "`.`" + tableName + "` SET `"
                + columnName + "`= ? " + " WHERE `" + idColumnName + "`='" + id + "';";

    }

    @Override
    public String BuildDeleteString(String databaseName, String tableName, String id) {
        return "DELETE FROM `" + databaseName + "`.`" + tableName + "` WHERE `author_id`='" + id + "';";
    }

}
