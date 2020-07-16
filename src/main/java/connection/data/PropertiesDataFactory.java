package connection.data;

import connection.data.PropertiesData;

public class PropertiesDataFactory {
    private static final PropertiesData INSTANCE = new PropertiesData();

    private PropertiesDataFactory() {
    }

    public static PropertiesData getInstance() {
        return INSTANCE;
    }
}
