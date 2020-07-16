package dao.impl;

import connection.JdbcConnection;
import dao.PaymentDao;
import entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    private static final String FIND_ALL_CLIENTS = "SELECT * FROM payment";
    private static final String FIND_BY_ID = "SELECT * FROM payment WHERE id = ?";
    private static final String CREATE_CLIENT = "INSERT INTO payment" +
            " (id,name,date,amount,id_account) " +
            "VALUES(?,?,?,?,?)";
    private static final String DELETE_CLIENT = "DELETE FROM payment WHERE id = ?";

    private final Connection connection = JdbcConnection.getConnection();

    @Override
    public List<Payment> findAll() throws SQLException {
        List<Payment> clients = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery(FIND_ALL_CLIENTS);
        while (resultSet.next()) {
            clients.add(new Payment(resultSet.getLong(1), resultSet.getString(2),
                    resultSet.getDate(3), resultSet.getLong(4),
                    resultSet.getLong(5)));
        }
        return clients;
    }

    @Override
    public Payment findById(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment client = new Payment();
        if (resultSet.next()) {
            client.setId(resultSet.getLong(1));
            client.setName(resultSet.getString(2));
            client.setDate(resultSet.getDate(3));
            client.setAmount(resultSet.getLong(4));
            client.setIdAccount(resultSet.getLong(5));
        }
        return client;
    }

    @Override
    public void create(Payment client) throws SQLException {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT);
        preparedStatement.setLong(1, client.getId());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setDate(3, date);
        preparedStatement.setLong(4, client.getAmount());
        preparedStatement.setLong(5, client.getIdAccount());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Payment client) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT);
        preparedStatement.setLong(1, client.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Payment t) throws SQLException {}

}
