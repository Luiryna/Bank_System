package connection;

import com.mysql.cj.jdbc.Driver;
import connection.data.PropertiesData;
import connection.data.PropertiesDataFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private Connector() {
    }

    public static Connection getConnection() {

        PropertiesData data = PropertiesDataFactory.getInstance();
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
