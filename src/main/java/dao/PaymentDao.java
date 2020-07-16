package dao;

import entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDao extends BaseDao<Payment>{
    List<Payment> findAll() throws SQLException;

    Payment findById(long id) throws SQLException;

    void create(Payment t) throws SQLException;

    void delete(Payment t) throws SQLException;

    void update(Payment t) throws SQLException;
}
