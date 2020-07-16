package connection;

public class DataForConnectToDatabaseFactory {
    private static final DataForConnectToDatabase INSTANCE = new DataForConnectToDatabase();

    private DataForConnectToDatabaseFactory() {
    }

    public static DataForConnectToDatabase getInstance() {
        return INSTANCE;
    }
}
