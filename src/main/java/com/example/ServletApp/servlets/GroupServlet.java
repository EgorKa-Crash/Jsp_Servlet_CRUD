package com.example.ServletApp.servlets;

import com.example.ServletApp.dao.GroupDAO;
import com.example.ServletApp.dao.UserDAO;
import com.example.ServletApp.entities.Groupp;
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
import java.sql.Timestamp;
import java.util.List;

@WebServlet( urlPatterns = {"/group","/newGroup","/insertGroup","/deleteGroup","/editGroup","/updateGroup"})
public class GroupServlet extends HttpServlet {
    private GroupDAO groupDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }

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
        request.setAttribute("listGroupp", listGroupp);
        request.setAttribute("error", new ErrorObj());
        RequestDispatcher dispatcher = request.getRequestDispatcher("groupList.jsp");
        dispatcher.forward(request, response);
        ErrorObj.setIsAble(false);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("groupForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("groupId"));
        Groupp existingGroupp = groupDAO.getGroupp(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("groupForm.jsp");
        request.setAttribute("group", existingGroupp);
        dispatcher.forward(request, response);
    }

    private void insertGroupp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String groupName = request.getParameter("groupName");
        String groupComment = request.getParameter("groupComment");
        Groupp newGroupp = new Groupp(groupName, groupComment);
        groupDAO.insertGroup(newGroupp);
        response.sendRedirect("group");
    }

    private void updateGroupp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("groupId"));
        String groupName = request.getParameter("groupName");
        String groupComment = request.getParameter("groupComment");
        String creatingStr = request.getParameter("creatingDate");
        Timestamp creatingDate = Timestamp.valueOf(creatingStr);

        Groupp updateGroupp = new Groupp(id, groupName, creatingDate, groupComment);
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

