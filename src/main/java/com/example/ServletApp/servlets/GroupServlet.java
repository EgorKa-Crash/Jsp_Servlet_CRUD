package com.example.ServletApp.servlets;

import com.example.ServletApp.dao.GroupDAO;
import com.example.ServletApp.dao.UserDAO;
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
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/groupp")
public class GroupServlet extends HttpServlet {
    private GroupDAO groupDAO;

    public void init() {
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
                case "/newGroup":
                    showNewForm(request, response);
                    break;
                case "/insertGroup":
                    insertGroupp(request, response);
                    break;
                case "/deleteGroup":
                    deleteGroupp(request, response);
                    break;
                case "/editGroup":
                    showEditForm(request, response);
                    break;
                case "/updateGroup":
                    updateGroupp(request, response);
                    break;
                default:
                    listGroupp(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listGroupp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Groupp> listGroupp = groupDAO.getAllOfGroup();
        //List<Userr> listUser = new ArrayList<>();
        //listUser.add(new Userr(1,"1","1","1","1"));
        //listUser.add(new Userr(2,"2","1","1","1"));
        //listUser.add(new Userr(3,"3","1","1","1"));
        request.setAttribute("listGroupp", listGroupp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("groupList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("grouppForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("grouppId"));
        Groupp existingGroupp = groupDAO.getGroupp(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("grouppForm.jsp");
        request.setAttribute("group", existingGroupp);
        dispatcher.forward(request, response);
    }

    private void insertGroupp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("groupId"));
        String groupName = request.getParameter("groupName");
        //Timestamp creatingDate = request.getParameter("creatingDate");
        String groupComment = request.getParameter("groupComment");
        Groupp newGroupp = new Groupp(id, groupName, groupComment);
        groupDAO.insertGroup(newGroupp);
        response.sendRedirect("group");
    }

    private void updateGroupp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("groupId"));
        String groupName = request.getParameter("groupName");
        String groupComment = request.getParameter("groupComment");

        Groupp updateGroupp = new Groupp(id, groupName, groupComment);
        groupDAO.updateGroupp(updateGroupp);
        response.sendRedirect("group");
    }

    private void deleteGroupp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("groupId"));
        groupDAO.deleteGroupp(id);
        response.sendRedirect("group");
    }
}

