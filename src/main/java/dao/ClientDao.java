package dao;

import connection.JdbcConnection;
import entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private static final String FIND_ALL_CLIENTS = "SELECT * FROM client";
    private static final String CREATE_CLIENT = "INSERT INTO client" +
            " (id,name,surname,login,password,mail) " +
            "VALUES(?,?,?,?,?,?)";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE id = ?";

    private final Connection connection = JdbcConnection.getConnection();

    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery(FIND_ALL_CLIENTS);
        while (resultSet.next()) {
            clients.add(new Client(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getString(6)));
        }
        return clients;
    }

    public Client create(Client client) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT);
        preparedStatement.setLong(1, client.getId());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setString(3, client.getSurname());
        preparedStatement.setString(4, client.getLogin());
        preparedStatement.setString(5, client.getPassword());
        preparedStatement.setString(6, client.getMail());
        preparedStatement.executeUpdate();
        return client;
    }

    public void delete(Client client) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT);
        preparedStatement.setLong(1, client.getId());
        preparedStatement.executeUpdate();
    }
}
