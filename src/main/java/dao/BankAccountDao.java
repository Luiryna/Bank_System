package dao;

import entity.BankAccount;

import java.sql.SQLException;
import java.util.List;

public interface BankAccountDao extends BaseDao<BankAccount> {

    List<BankAccount> findAll() throws SQLException;

    BankAccount findById(long id) throws SQLException;

    void create(BankAccount t) throws SQLException;

    void update(BankAccount t) throws SQLException;

    void delete(BankAccount t) throws SQLException;

}
