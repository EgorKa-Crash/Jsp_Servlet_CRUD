package com.example.ServletApp.dao;

import com.example.ServletApp.entities.Userr;
import com.example.ServletApp.exception.ErrorObj;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static void insertUser(Userr userr) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try{
            session.save(userr);
            session.getTransaction().commit();
        }
        catch (Exception e){
            ErrorObj error = new ErrorObj(true,"Ошибка добавления пользователя");
        }
        session.close();
    }

    public static void updateUser(Userr userr) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(userr);
            session.getTransaction().commit();
        }
        catch (Exception e){
            ErrorObj error = new ErrorObj(true,"Ошибка изменения пользователя");
        }

        session.close();
    }

    public static void deleteUser(int Id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.remove(session.get(Userr.class,Id));
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
