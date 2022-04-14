package com.example.ServletApp.servlets;

import com.example.ServletApp.dao.SubscriptionsDAO;
import com.example.ServletApp.dao.UserDAO;
import com.example.ServletApp.entities.Subscriptions;
import com.example.ServletApp.entities.Userr;
import com.example.ServletApp.exception.ErrorObj;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/subscriptions", "/newSubscriptions", "/insertSubscriptions", "/deleteSubscriptions", "/editSubscriptions" })
public class SubscriptionServlet extends HttpServlet {
    private SubscriptionsDAO subscriptionsDAO;
    private UserDAO userDAO;

    public void init() {
        subscriptionsDAO = new SubscriptionsDAO();
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/newSubscriptions":
                    showNewForm(request, response);
                    break;
                case "/insertSubscriptions":
                    insertSubscriptions(request, response);
                    break;
                case "/deleteSubscriptions":
                    deleteSubscriptions(request, response);
                    break;
                default:
                    listSubscriptions(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listSubscriptions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Subscriptions> listSubscriptions = subscriptionsDAO.getAllOfSubscriptions();
        request.setAttribute("listSubscriptions", listSubscriptions);
        request.setAttribute("error", new ErrorObj());
        RequestDispatcher dispatcher = request.getRequestDispatcher("subscriptionsList.jsp");
        dispatcher.forward(request, response);
        ErrorObj.setIsAble(false);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Userr> listUser = userDAO.getAllOfUsers();
        List<Userr> subscribers = userDAO.getAllOfUsers();
        request.setAttribute("users", listUser);
        request.setAttribute("subscribers", subscribers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("subscriptionsForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertSubscriptions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String userString = request.getParameter("userId");
        String subscriberString = request.getParameter("subscriberId");
        subscriberString = subscriberString.replaceFirst(".*?(\\d+).*", "$1");
        userString = userString.replaceFirst(".*?(\\d+).*", "$1");
        int userId = Integer.parseInt(userString);
        int subscriberId = Integer.parseInt(subscriberString);
        Userr user = userDAO.getUser(userId);
        Userr subscriber = userDAO.getUser(subscriberId);
        Subscriptions newSubscriptions = new Subscriptions(user, subscriber);
        subscriptionsDAO.insertSubscriptions(newSubscriptions);
        response.sendRedirect("subscriptions");
    }

    private void deleteSubscriptions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int subscriberId = Integer.parseInt(request.getParameter("subscriberId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        Userr user = userDAO.getUser(userId);
        Userr subscriber = userDAO.getUser(subscriberId);
        subscriptionsDAO.deleteSubscriptions(user, subscriber);
        response.sendRedirect("subscriptions");
    }
}