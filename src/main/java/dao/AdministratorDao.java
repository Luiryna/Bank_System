package dao;

import connection.JdbcConnection;
import entity.Administrator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDao {
    private static final String FIND_ALL_ADMINS = "SELECT * FROM administrator";

    private final Connection connection = JdbcConnection.getConnection();

    public List<Administrator> findAll() throws SQLException {
        List<Administrator> administrators = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery(FIND_ALL_ADMINS);
        while (resultSet.next()) {
            administrators.add(new Administrator(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3)));
        }
        return administrators;
    }

//    public Administrator getById() {
//
//    }
}
