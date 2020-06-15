package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/bank?useUnicode=true&serverTimezone=UTC";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    private static Connection connection;

    /*TODO:
            1) хранимые процедуры для инициализации, удвления и заполнения бд стартовыми данными
            2) создать connection pool
            3) исключения для слоев
            4) фабрика для дао?
     */

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
