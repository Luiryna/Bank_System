package dao;

import dao.impl.BankCardDaoImpl;
import entity.BankCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BankCardDaoTest {
    private BankCardDao bankCardDao;
    private BankCard bankCard;

    @BeforeEach
    void initialize() {
        bankCardDao = new BankCardDaoImpl() {
        };
        bankCard = new BankCard(1, "1111222233334444", 1, 1);
    }

    @Test
    void findAll() throws SQLException {
        List<BankCard> clients = bankCardDao.findAll();
        System.out.println(clients.toString());
        assertNotNull(clients);
    }

    @Test
    void create() throws SQLException {
        bankCardDao.create(bankCard);
    }
}
