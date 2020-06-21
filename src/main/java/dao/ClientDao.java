package dao;

import entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends BaseDao<Client>{

    public List<Client> findAll() throws SQLException;

    public Client findById(long id);

    public void create(Client t) throws SQLException;

    public void update(Client t) throws SQLException;

    public void delete(Client t) throws SQLException;


}
