package dao;

public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();


    private DaoFactory() {

    }

    private static DaoFactory getInstance() {
        return instance;
    }


}
