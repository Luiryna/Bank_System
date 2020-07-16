package dao;

import dao.impl.PaymentDaoImpl;
import entity.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentDaoTest {
    private PaymentDao clientDao;
    private Payment client;

    @BeforeEach
    void initialize() {
        clientDao = new PaymentDaoImpl();
        client = new Payment(1, "perevod", 100, 1);
    }

    @Test
    void findAll() throws SQLException {
        List<Payment> clients = clientDao.findAll();
        System.out.println(clients.toString());
        assertNotNull(clients);
    }

    @Test
    void create() throws SQLException {
        clientDao.create(client);
    }
}
