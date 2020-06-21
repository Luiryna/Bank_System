package dao;
import dao.impl.BankAccountDaoImpl;
import dao.impl.BankCardDaoImpl;
import dao.impl.ClientDaoImpl;

public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();

    private BankAccountDao bankAccountDao = new BankAccountDaoImpl();
    private BankCardDao bankCardDao = new BankCardDaoImpl();
    private ClientDao clientDao = new ClientDaoImpl();

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public BankCardDao getBankCardDao() {
        return bankCardDao;
    }

    public BankAccountDao getBankAccountDao() {
        return bankAccountDao;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }
}
