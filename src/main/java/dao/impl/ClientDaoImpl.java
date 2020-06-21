package dao.impl;

import connection.JdbcConnection;
import dao.ClientDao;
import entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private static final String FIND_ALL_CLIENTS = "SELECT * FROM client";
    private static final String FIND_BY_ID_CLIENT = "SELECT * FROM client WHERE id=?";
    private static final String CREATE_CLIENT = "INSERT INTO client" +
            " (id,name,surname,login,password,mail) " +
            "VALUES(?,?,?,?,?,?)";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE id = ?";
    private static final String UPDATE_CLIENT = "UPDATE client SET name = ?, surname = ?, login = ?, " +
            "password= ?, mail = ? WHERE id = ?";

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

    public Client findById(long id) {
        Client client = new Client();
        return client;
    }

    public void create(Client client) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT);
        preparedStatement.setLong(1, client.getId());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setString(3, client.getSurname());
        preparedStatement.setString(4, client.getLogin());
        preparedStatement.setString(5, client.getPassword());
        preparedStatement.setString(6, client.getMail());
        preparedStatement.executeUpdate();
    }

    public void delete(Client client) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT);
        preparedStatement.setLong(1, client.getId());
        preparedStatement.executeUpdate();
    }

    public void update(Client client) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT);
        preparedStatement.setString(1, client.getName());
        preparedStatement.setString(2, client.getSurname());
        preparedStatement.setString(3, client.getLogin());
        preparedStatement.setString(4, client.getPassword());
        preparedStatement.setString(5, client.getMail());
        preparedStatement.setLong(6, client.getId());
        preparedStatement.executeUpdate();
    }
}