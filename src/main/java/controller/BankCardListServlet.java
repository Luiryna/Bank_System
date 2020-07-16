package controller;

import dao.BankAccountDao;
import dao.BankCardDao;
import dao.impl.BankAccountDaoImpl;
import dao.impl.BankCardDaoImpl;
import entity.BankCard;
import entity.Client;
import util.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = { "/productList" })
public class BankCardListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BankCardListServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BankCardDaoImpl bankCardDao = new BankCardDaoImpl();
        HttpSession session = request.getSession();
        Client loginedUser = MyUtils.getLoginedUser(session);

        long id = loginedUser.getId();

        String errorString = null;
        List<BankCard> list = null;
        try {
            list = bankCardDao.findAll(id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("cardList", list);

        // Forward к /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/productListView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
