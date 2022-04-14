package com.example.ServletApp.servlets;

import com.example.ServletApp.dao.PostDAO;
import com.example.ServletApp.dao.UserDAO;
import com.example.ServletApp.entities.Post;
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

@WebServlet( urlPatterns = {"/post","/newPost","/insertPost","/deletePost","/editPost","/updatePost"})
public class PostServlet  extends HttpServlet {
    private PostDAO postDAO;
    private UserDAO userDAO;

    public void init() {
        postDAO = new PostDAO();
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
                case "/newPost":
                    showNewForm(request, response);
                    break;
                case "/insertPost":
                    insertPost(request, response);
                    break;
                case "/deletePost":
                    deletePost(request, response);
                    break;
                case "/editPost":
                    showEditForm(request, response);
                    break;
                case "/updatePost":
                    updatePost(request, response);
                    break;
                default:
                    listPost(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Post> listPost = postDAO.getAllOfPost();
        request.setAttribute("listPost", listPost);
        request.setAttribute("error", new ErrorObj());
        RequestDispatcher dispatcher = request.getRequestDispatcher("postList.jsp");
        dispatcher.forward(request, response);
        ErrorObj.setIsAble(false);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Userr> listUser = userDAO.getAllOfUsers();
        request.setAttribute("users", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("postForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("postId"));
        Post existingPost = postDAO.getPost(id);
        List<Userr> listUser = userDAO.getAllOfUsers();
        RequestDispatcher dispatcher = request.getRequestDispatcher("postForm.jsp");
        request.setAttribute("post", existingPost);
        request.setAttribute("users", listUser);
        dispatcher.forward(request, response);
    }

    private void insertPost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String postHeader = request.getParameter("postHeader");
        String postComment = request.getParameter("postContent");
        String userString = request.getParameter("userId");
        userString = userString.replaceFirst(".*?(\\d+).*", "$1");
        int userId = Integer.parseInt(userString);
        Userr user = userDAO.getUser(userId);
        Post newPost = new Post(postHeader, postComment,user);
        postDAO.insertPost(newPost);
        response.sendRedirect("post");
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int postId = Integer.parseInt(request.getParameter("postId"));
        String postHeader = request.getParameter("postHeader");
        String createStr = request.getParameter("createDate");
        Timestamp createDate = Timestamp.valueOf(createStr);
        String postComment = request.getParameter("postContent");
        String userString = request.getParameter("userId");
        userString = userString.replaceFirst(".*?(\\d+).*", "$1");
        int userId = Integer.parseInt(userString);
        Userr user = userDAO.getUser(userId);
        Post updatePost = new Post(postId, postHeader, createDate, postComment,user);
        postDAO.updatePost(updatePost);
        response.sendRedirect("post");
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("postId"));
        postDAO.deletePost(id);
        response.sendRedirect("post");
    }
}

