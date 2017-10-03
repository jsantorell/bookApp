package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.QueryString;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jeremy Santorelli
 */
public class PutQueryTogetherForMySql implements PutQueryTogether {

    private static PutQueryTogetherForMySql query;

    private PutQueryTogetherForMySql() {
    }

    /**
     *
     * @return a ParkingFactory and ensures only one was made.
     */
    public static PutQueryTogetherForMySql getInstance() {

        if (PutQueryTogetherForMySql.query == null) {
            PutQueryTogetherForMySql.query = new PutQueryTogetherForMySql();

        }

        return PutQueryTogetherForMySql.query;

    }

    @Override
    public String BuildCreateString(String databaseName, String tableName, List<String> columns, List<List<String>> dataSets) {

        String columnsSet = "";
        String queryString = "";

        for (int i = 0; i < columns.size(); i++) {

            if (columns.get(i).equals("*")) {

                columnsSet += "*";

            } else if (i == 0) {

                columnsSet += "`" + columns.get(i) + "`";

            } else {

                columnsSet += ",`" + columns.get(i) + "`";
            }

        }

        String c = "INSERT INTO `" + databaseName + "`.`" + tableName + "` (" + columnsSet + ")";

        for (List<String> rec : dataSets) {//Will need to fix in the future for integers, doubles, etc.

            String insertValues = "";

            for (int value = 0; value < rec.size(); value++) {

                if (value == 0) {

                    insertValues += "'" + rec.get(value) + "'";
                } else {

                    insertValues += ",'" + rec.get(value) + "'";
                }
            }

            queryString += c + "VALUES (" + insertValues + ");";
        }

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
            String columnName, String value, String idColumnName, int id) {
   
        return "UPDATE `" + databaseName + "`.`" + tableName + "` SET `"
                + columnName + "`='" + value + "' WHERE `" + idColumnName + "`='" + id + "';";

    }

    @Override
    public String BuildDeleteString(String databaseName, String tableName, int id) {
        return "DELETE FROM `" + databaseName + "`.`" + tableName + "` WHERE `author_id`='" + id + "';";
    }

}
