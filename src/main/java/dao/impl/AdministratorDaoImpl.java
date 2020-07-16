package dao.impl;

import connection.Connector;
import dao.AdministratorDao;
import entity.Administrator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDaoImpl implements AdministratorDao {
    private static final String FIND_ALL_ADMINS = "SELECT * FROM administrator";

    private final Connection connection = Connector.getConnection();

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
