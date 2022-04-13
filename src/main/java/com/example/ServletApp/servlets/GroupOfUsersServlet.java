package com.example.ServletApp.servlets;

import com.example.ServletApp.dao.GroupDAO;
import com.example.ServletApp.dao.GroupOfUsersDAO;
import com.example.ServletApp.dao.UserDAO;
import com.example.ServletApp.entities.GroupOfUsers;
import com.example.ServletApp.entities.Groupp;
import com.example.ServletApp.entities.Userr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(urlPatterns = {"/groupOfUsers", "/newGroupOfUsers", "/insertGroupOfUsers", "/deleteGroupOfUsers", "/editGroupOfUsers" }) //, "/updateGroupOfUsers"
public class GroupOfUsersServlet extends HttpServlet {
    private GroupOfUsersDAO groupOfUsersDAO;
    private UserDAO userDAO;
    private GroupDAO groupDAO;

    public void init() {
        groupOfUsersDAO = new GroupOfUsersDAO();
        userDAO = new UserDAO();
        groupDAO = new GroupDAO();
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
                case "/newGroupOfUsers":
                    showNewForm(request, response);
                    break;
                case "/insertGroupOfUsers":
                    insertGroupOfUsers(request, response);
                    break;
                case "/deleteGroupOfUsers":
                    deleteGroupOfUsers(request, response);
                    break;
//                case "/editGroupOfUsers":
//                    showEditForm(request, response);
//                    break;
                //case "/updateGroupOfUsers":
                    //updateGroupOfUsers(request, response);
                    //break;
                default:
                    listGroupOfUsers(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listGroupOfUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<GroupOfUsers> listGroupOfUsers = groupOfUsersDAO.getAllOfGroupOfUsers();
        request.setAttribute("listGroupOfUsers", listGroupOfUsers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("groupOfUsersList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Userr> listUser = userDAO.getAllOfUsers();
        List<Groupp> listGroup = groupDAO.getAllOfGroup();
        request.setAttribute("users", listUser);
        request.setAttribute("groups", listGroup);
        RequestDispatcher dispatcher = request.getRequestDispatcher("groupOfUsersForm.jsp");
        dispatcher.forward(request, response);
    }

//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        int groupId = Integer.parseInt(request.getParameter("groupId"));
//        GroupOfUsers existingGroupOfUsers = groupOfUsersDAO.getGroupOfUsers(userId, groupId);
//        List<Userr> listUser = userDAO.getAllOfUsers();
//        List<Groupp> listGroup = groupDAO.getAllOfGroup();
//        RequestDispatcher dispatcher = request.getRequestDispatcher("groupOfUsersForm.jsp");
//        request.setAttribute("groupOfUsers", existingGroupOfUsers);
//        request.setAttribute("users", listUser);
//        request.setAttribute("groups", listGroup);
//        dispatcher.forward(request, response);
//    }

    private void insertGroupOfUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //String groupOfUsersHeader = request.getParameter("groupOfUsersHeader");
        //String groupOfUsersComment = request.getParameter("groupOfUsersContent");
        String userString = request.getParameter("userId");
        String groupString = request.getParameter("groupId");
        groupString = groupString.replaceFirst(".*?(\\d+).*", "$1");
        userString = userString.replaceFirst(".*?(\\d+).*", "$1");
        int userId = Integer.parseInt(userString);
        int groupId = Integer.parseInt(groupString);
        Userr user = userDAO.getUser(userId);
        Groupp group = groupDAO.getGroupp(groupId);
        GroupOfUsers newGroupOfUsers = new GroupOfUsers(user, group);
        groupOfUsersDAO.insertGroupOfUsers(newGroupOfUsers);
        response.sendRedirect("groupOfUsers");
    }

//    private void updateGroupOfUsers(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//
//        int groupOfUsersId = Integer.parseInt(request.getParameter("groupOfUsersId"));
//        String groupOfUsersHeader = request.getParameter("groupOfUsersHeader");
//        String createStr = request.getParameter("createDate");
//        Timestamp createDate = Timestamp.valueOf(createStr);
//        String groupOfUsersComment = request.getParameter("groupOfUsersContent");
//        String userString = request.getParameter("userId");
//        userString = userString.replaceFirst(".*?(\\d+).*", "$1");
//        int userId = Integer.parseInt(userString);
//        GroupOfUsers updateGroupOfUsers = new GroupOfUsers(userId, groupId);
//        groupOfUsersDAO.updateGroupOfUsers(updateGroupOfUsers);
//        response.sendRedirect("groupOfUsers");
//    }

    private void deleteGroupOfUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        Userr user = userDAO.getUser(userId);
        Groupp group = groupDAO.getGroupp(groupId);
        groupOfUsersDAO.deleteGroupOfUsers(user, group);
        response.sendRedirect("groupOfUsers");
    }
}

