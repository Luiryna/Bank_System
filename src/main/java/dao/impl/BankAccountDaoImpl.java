package dao.impl;

import connection.JdbcConnection;
import dao.BankAccountDao;
import entity.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDaoImpl implements BankAccountDao {
    private static final String FIND_ALL_BANK_ACCOUNTS = "SELECT * FROM bank_account";
    private static final String FIND_BY_ID = "SELECT * FROM bank_account WHERE id = ?";
    private static final String CREATE_BANK_ACCOUNT = "INSERT INTO bank_account" +
            " (id,name,amount_money,is_blocked) " +
            "VALUES(?,?,?,?)";
    private static final String DELETE_BANK_ACCOUNT = "DELETE FROM bank_account WHERE id = ?";
    private static final String UPDATE_BANK_ACCOUNT = "UPDATE bank_account SET name = ?, amount_money = ?, is_blocked = ? " +
            " WHERE id = ?";

    private final Connection connection = JdbcConnection.getConnection();

    @Override
    public List<BankAccount> findAll() throws SQLException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery(FIND_ALL_BANK_ACCOUNTS);
        while (resultSet.next()) {
            bankAccounts.add(new BankAccount(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getLong(3), resultSet.getBoolean(4)));
        }
        return bankAccounts;
    }

    @Override
    public BankAccount findById(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        BankAccount bankAccount = new BankAccount();
        if (resultSet.next()) {
            bankAccount.setId(resultSet.getLong(1));
            bankAccount.setName(resultSet.getString(2));
            bankAccount.setAmountMoney(resultSet.getLong(3));
            //bankAccount.isBlocked(resultSet.getBoolean(4));
        }
        return bankAccount;
    }


    @Override
    public void create(BankAccount bankAccount) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BANK_ACCOUNT);
        preparedStatement.setLong(1, bankAccount.getId());
        preparedStatement.setString(2, bankAccount.getName());
        preparedStatement.setLong(3, bankAccount.getAmountMoney());
        preparedStatement.setBoolean(4, bankAccount.isBlocked());
        preparedStatement.executeUpdate();
    }


    @Override
    public void delete(BankAccount bankAccount) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BANK_ACCOUNT);
        preparedStatement.setLong(1, bankAccount.getId());
        preparedStatement.executeUpdate();
    }


    @Override
    public void update(BankAccount bankAccount) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BANK_ACCOUNT);
        preparedStatement.setString(1, bankAccount.getName());
        preparedStatement.setLong(2, bankAccount.getAmountMoney());
        preparedStatement.setBoolean(3, bankAccount.isBlocked());
        preparedStatement.setLong(4, bankAccount.getId());
        preparedStatement.executeUpdate();
    }
}
