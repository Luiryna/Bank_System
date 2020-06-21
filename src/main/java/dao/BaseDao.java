package dao;

import entity.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    public List<T> findAll() throws SQLException;

    public T findById(long id);

    public void create(T t) throws SQLException;

    public void update(T t) throws SQLException;

    public void delete(T t) throws SQLException;
}
