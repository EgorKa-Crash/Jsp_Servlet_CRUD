package com.example.ServletApp.dao;

import com.example.ServletApp.entities.Userr;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static void insertUser(Userr userr) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(userr);
        session.getTransaction().commit();
        session.close();
    }

//    public static void updateUser(int Id, String login, String password, String email, String nickName) {
//
//        Userr userr = new Userr(Id, login, password, email, nickName);
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
//        session.update(userr);
//        session.getTransaction().commit();
//        session.close();
//    }

    public static void updateUser(Userr userr) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(userr);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteUser(int Id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Userr WHERE userId = " + Id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static Userr getUser(int Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Userr user = session.get(Userr.class,Id);
        session.close();
        return user;
    }


    public static List<Userr> getAllOfUsers() {
        List<Userr> users = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        users = loadAllData(Userr.class, session);
        //users.stream().forEach(x -> System.out.println(x.toString()));
        session.close();
        return users;
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
