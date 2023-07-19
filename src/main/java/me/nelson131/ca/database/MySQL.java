package me.nelson131.ca.database;

import java.sql.*;

import static me.nelson131.ca.utils.Config.getCFG;

public class MySQL {

    private static final String url = getCFG("sql-url");
    private static final String user = getCFG("sql-user");
    private static final String password = getCFG("sql-password");

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    static {
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "userID LONG," +
                    "messageID LONG, " +
                    "name VARCHAR(30));");

            System.out.println("sql table init");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addData(Long userId, Long messageId, String name) throws SQLException{
        String query = "INSERT INTO users (userID, messageID, name) VALUES (" + userId + ", " + messageId + ", '" + name + "');";
        statement.executeUpdate(query);
    }
}
