import dao.impl.ClientDaoImpl;
import entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientDaoTest {
    private ClientDaoImpl clientDao;
    private Client client;

    @BeforeEach
    void initialize() {
        clientDao = new ClientDaoImpl();
        client = new Client(1, "Iryna", "Lukashevich", "login", "password",
                "luiryna322@gmail.com");
    }

    @Test
    void findAll() throws SQLException {
        List<Client> clients = clientDao.findAll();
        System.out.println(clients.toString());
        assertNotNull(clients);
    }

    @Test
    void create() throws SQLException {
        clientDao.create(client);
    }

    @Test
    void delete() throws SQLException {
        clientDao.delete(client);
        List<Client> clients = clientDao.findAll();
        String result = "[]";
        assertEquals(clients.toString(), result);
        //просто пустой список получился
    }

    @Test
    void update() {

    }
}
