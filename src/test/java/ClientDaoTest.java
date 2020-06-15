import dao.AdministratorDao;
import dao.ClientDao;
import entity.Administrator;
import entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientDaoTest {
    private ClientDao clientDao;
    private Client client;

    @BeforeEach
    void initialize() {
        clientDao = new ClientDao();
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
        Client newClient = clientDao.create(client);
        assertNotNull(newClient);
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
