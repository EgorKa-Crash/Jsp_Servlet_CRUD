package com.example.ServletApp;

import com.example.ServletApp.dao.GroupDAO;
import com.example.ServletApp.dao.PostDAO;
import com.example.ServletApp.dao.UserDAO;
import com.example.ServletApp.entities.Groupp;
import com.example.ServletApp.entities.Post;
import com.example.ServletApp.entities.Userr;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //GroupDAO groupDAO = new GroupDAO();
//        List<Userr> listUser = userDAO.getAllOfUsers();
//        listUser.stream().forEach(System.out::println);
//
//        Userr existingUser = userDAO.getUser(1);
//        System.out.println(existingUser);

        //List<Groupp> listGroup = GroupDAO.getAllOfGroup();
        //listGroup.stream().forEach(System.out::println);

        List<Post> listGroup = PostDAO.getAllOfPost();
        listGroup.stream().forEach(System.out::println);

    }
}
