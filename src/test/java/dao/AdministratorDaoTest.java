package dao;

import dao.impl.AdministratorDaoImpl;
import entity.Administrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdministratorDaoTest {
    private AdministratorDaoImpl administratorDao;
    private Administrator administrator;

    @BeforeEach
    void initialize() {
        administratorDao = new AdministratorDaoImpl();
    }

    @Test
    void findAll() throws SQLException {
        List<Administrator> administrators = administratorDao.findAll();
        System.out.println(administrators.toString());
        assertNotNull(administrators);
    }
}
