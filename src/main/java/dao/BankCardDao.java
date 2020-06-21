package dao;

import entity.BankCard;

import java.sql.SQLException;
import java.util.List;

public interface BankCardDao extends BaseDao<BankCard> {

    List<BankCard> findAll() throws SQLException;

    BankCard findById(long id) throws SQLException;

    void create(BankCard t) throws SQLException;

    void update(BankCard t) throws SQLException;

    void delete(BankCard t) throws SQLException;
}
