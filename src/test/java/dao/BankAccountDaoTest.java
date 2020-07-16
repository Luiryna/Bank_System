package dao;

import dao.impl.BankAccountDaoImpl;
import entity.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BankAccountDaoTest {

    private BankAccountDao bankAccountDao;
    private BankAccount bankAccount;

    @BeforeEach
    void initialize() {
        bankAccountDao = new BankAccountDaoImpl() {
        };
        bankAccount = new BankAccount(1, "Main", 200000, 0);
    }

    @Test
    void findAll() throws SQLException {
        List<BankAccount> clients = bankAccountDao.findAll();
        System.out.println(clients.toString());
        assertNotNull(clients);
    }

    @Test
    void create() throws SQLException {
        bankAccountDao.create(bankAccount);
    }
}
