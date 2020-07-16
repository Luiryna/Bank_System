package connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private  BlockingQueue<ProxyConnection> connectionQueue;
    private static int poolSize;

    private ConnectionPool() {
        if (instanceCreated.get()) {
            //log
            throw new RuntimeException("Tried to clone connection pool with reflection api");
        }
        registerJDBCDriver();
        initPool();
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (!instanceCreated.get()) {// null
                    instance = new ConnectionPool();

                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = connectionQueue.take();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void destroyConnections() {
        for (int index = 0; index < poolSize; index++) {
            ProxyConnection proxyConnection = null;
            try {
                proxyConnection = connectionQueue.take();
            } catch (InterruptedException e) {
///khjlkhlkhl;kh;l
            } finally {
                if (proxyConnection != null) {
                    try {
                        proxyConnection.closeConnection();
                    } catch (RuntimeException e) {
///jkhlkjhlkhlkhjlkhklj
                    }
                }
            }
        }

        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }

        } catch (SQLException e) {
//jgjkgjkgjggkj
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        if (instanceCreated.get()) {

            throw new CloneNotSupportedException("Tried to clone connection pool");
        }
        return super.clone();
    }

    public void releaseConnection(ProxyConnection proxyConnection) {
        try {
            connectionQueue.put(proxyConnection);

        } catch (InterruptedException e) {
//jgjghjghjghjghjgjhghjghjghj
        }
    }

    private static void registerJDBCDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        } catch (SQLException e){
            //log
            throw new RuntimeException("Mysql jdbc driver hasn't loaded", e);
        }
    }

    private  void initPool() {
        final String DATABASE_PROPERTY = "application";
        final String DATABASE_URL = "db.url";
        final String DATABASE_USER = "db.user";
        final String DATABASE_PASSWORD = "db.password";
        final String DATABASE_CHARACTER_ENCODING = "db.characterEncoding";
        final String DATABASE_USE_UNICODE = "db.useUnicode";
        final String DATABASE_POOL_SIZE = "db.poolSize";

        ResourceBundle resourceBundle;
        try {
            resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTY);
        } catch (MissingResourceException e) {
//log
            throw new RuntimeException("Hasn't found bundle for database");
        }

        final String URL = resourceBundle.getString(DATABASE_URL);
//        final String USER = resourceBundle.getString(DATABASE_USER);
//        final String PASSWORD = resourceBundle.getString(DATABASE_PASSWORD);
//        final String CHARACTER_ENCODING = resourceBundle.getString(DATABASE_CHARACTER_ENCODING);
//        final String USE_UNICODE = resourceBundle.getString(DATABASE_USE_UNICODE);
        final String POOL_SIZE = resourceBundle.getString(DATABASE_POOL_SIZE);

//        Properties properties = new Properties();
//        properties.put("userDAO", USER);
//        properties.put("password", PASSWORD);
//        properties.put("characterEncoding", CHARACTER_ENCODING);
//        properties.put("useUnicode", USE_UNICODE);

        poolSize = Integer.parseInt(POOL_SIZE);
        connectionQueue = new ArrayBlockingQueue<ProxyConnection>(poolSize);

        for (int index = 0; index < poolSize; index++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(URL);
            } catch (SQLException e) {

                throw new RuntimeException("Hasn't found connection with database");
            }

            ProxyConnection proxyConnection = new ProxyConnection(connection);
            try {
                connectionQueue.put(proxyConnection);
            } catch (InterruptedException e) {

            }
        }

    }


}