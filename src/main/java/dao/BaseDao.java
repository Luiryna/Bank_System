package dao;

import entity.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    List<T> findAll() throws SQLException;

    T findById(long id) throws SQLException;

    void create(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;
}
