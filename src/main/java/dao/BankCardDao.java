package dao;

import connection.JdbcConnection;
import entity.BankCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankCardDao {
    private static final String FIND_ALL_BANK_CARDS = "SELECT * FROM bank_card";
    private static final String CREATE_BANK_CARD = "INSERT INTO bank_card" +
            " (id,name,id_user,id_account) " +
            "VALUES(?,?,?,?)";
    private static final String DELETE_BANK_CARD = "DELETE FROM bank_card WHERE id = ?";
    private static final String UPDATE_BANK_CARD = "UPDATE bank_card SET name = ?, id_user = ?, id_account = ? " +
            " WHERE id = ?";

    private final Connection connection = JdbcConnection.getConnection();

    public List<BankCard> findAll() throws SQLException {
        List<BankCard> bankCards = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery(FIND_ALL_BANK_CARDS);
        while (resultSet.next()) {
            bankCards.add(new BankCard(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getLong(3), resultSet.getLong(4)));
        }
        return bankCards;
    }

    public BankCard findById(long id) {
        BankCard bankCard = new BankCard();
        return bankCard;
    }

    public BankCard create(BankCard bankCard) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BANK_CARD);
        preparedStatement.setLong(1, bankCard.getId());
        preparedStatement.setString(2, bankCard.getName());
        preparedStatement.setLong(3, bankCard.getUserId());
        preparedStatement.setLong(4, bankCard.getAccountId());
        preparedStatement.executeUpdate();
        return bankCard;
    }

    public void delete(BankCard bankCard) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BANK_CARD);
        preparedStatement.setLong(1, bankCard.getId());
        preparedStatement.executeUpdate();
    }

    public BankCard update(BankCard bankCard) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BANK_CARD);
        preparedStatement.setString(1, bankCard.getName());
        preparedStatement.setLong(2, bankCard.getUserId());
        preparedStatement.setLong(3, bankCard.getAccountId());
        preparedStatement.setLong(4, bankCard.getId());
        preparedStatement.executeUpdate();
        return bankCard;
    }
}
