package connection;

public class Connector {
    private Connector() {
    }

    public static Connection getConnection() {

        DataForConnectToDatabase data = DataForConnectToDatabaseFactory.getInstance();
        Connection connection = null;

        try {

            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            Class.forName("com.mysql.cj.jdbc.Driver"); //нужен ли он ??

            connection = BasicConnectionPool.create(
                    data.getUrl(),
                    data.getUser(),
                    data.getPassword())

                    .getConnection();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
