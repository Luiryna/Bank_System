import dao.AdministratorDao;
import entity.Administrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DaoTest {
    private AdministratorDao administratorDao;
    private Administrator administrator;

    @BeforeEach
    void initialize() {
        administratorDao = new AdministratorDao();
    }

    @Test
    void findAll() throws SQLException {
        List<Administrator> administrators = administratorDao.findAll();
        System.out.println(administrators.toString());
        assertNotNull(administrators);
    }
}
