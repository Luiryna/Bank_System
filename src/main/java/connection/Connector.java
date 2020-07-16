package connection;

import com.mysql.cj.jdbc.Driver;

import connection.BasicConnectionPool;
import connection.DataForConnectToDatabase;
import connection.DataForConnectToDatabaseFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private Connector() {
    }

    public static Connection getConnection() {

        DataForConnectToDatabase data = DataForConnectToDatabaseFactory.getInstance();
        Connection connection = null;

        try {

            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = BasicConnectionPool.create(
                    data.getUrl(),
                    data.getUser(),
                    data.getPassword())

                    .getConnection();

        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
