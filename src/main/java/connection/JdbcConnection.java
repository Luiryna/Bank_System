package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/bank?useUnicode=true&serverTimezone=UTC";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection()  {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
