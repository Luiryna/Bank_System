package controller;

import dao.impl.ClientDaoImpl;
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

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        Client client = null;
        boolean hasError = false;
        String errorString = null;

        if (login == null || password == null || login.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            //Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Найти user в DB.
                ClientDaoImpl clientDao = new ClientDaoImpl();
                client = clientDao.findUser(login, password);

                if (client == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        if (hasError) {
            client = new Client();
            client.setLogin(login);
            client.setPassword(password);

            request.setAttribute("errorString", errorString);
            request.setAttribute("user", client);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, client);

            if (remember) {
                MyUtils.storeUserCookie(response, client);
            }
            else {
                MyUtils.deleteUserCookie(response);
            }

            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

}