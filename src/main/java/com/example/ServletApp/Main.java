package com.example.ServletApp;

import com.example.ServletApp.dao.GroupDAO;
import com.example.ServletApp.dao.GroupOfUsersDAO;
import com.example.ServletApp.dao.PostDAO;
import com.example.ServletApp.dao.UserDAO;
import com.example.ServletApp.entities.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //GroupDAO groupDAO = new GroupDAO();
        UserDAO userDAO = new UserDAO();
        List<Subscriptions> listUser = userDAO.getAllOfUsers().get(2).getSubscribers();


        //System.out.println(listUser.stream().count());
        //listUser.stream().forEach(System.out::println);
//
//        Userr existingUser = userDAO.getUser(1);
//        System.out.println(existingUser);

        //List<Groupp> listGroup = GroupDAO.getAllOfGroup();
        //listGroup.stream().forEach(System.out::println);

        //List<Post> listGroup = PostDAO.getAllOfPost();
        //listGroup.stream().forEach(System.out::println);



        //List<GroupOfUsers> listGroup = GroupOfUsersDAO.getAllOfGroupOfUsers();
        //listGroup.stream().forEach(System.out::println);

        //System.out.println(GroupOfUsersDAO.getGroupOfUsers(1,1));

    }
}
