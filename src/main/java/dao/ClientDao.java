package dao;

import entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends BaseDao<Client> {

    List<Client> findAll() throws SQLException;

    Client findById(long id) throws SQLException;

    void create(Client t) throws SQLException;

    void update(Client t) throws SQLException;

    void delete(Client t) throws SQLException;


}
