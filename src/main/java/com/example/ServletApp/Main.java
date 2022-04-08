package com.example.ServletApp;

import com.example.ServletApp.dao.UserDAO;
import com.example.ServletApp.entities.Userr;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        List<Userr> listUser = userDAO.getAllOfUsers();
        listUser.stream().forEach(System.out::println);

        Userr existingUser = userDAO.getUser(1);
        System.out.println(existingUser);
    }
}
