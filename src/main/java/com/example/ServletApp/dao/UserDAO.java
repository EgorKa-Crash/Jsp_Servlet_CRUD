package com.example.ServletApp.dao;

import com.example.ServletApp.entities.Userr;
import com.example.ServletApp.exception.ErrorObj;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDAO {

    public static void insertUser(Userr userr) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.save(userr);
            session.getTransaction().commit();
        } catch (Exception e) {
            ErrorObj error = new ErrorObj(true, "Ошибка добавления пользователя");
        }
    }

    public static void updateUser(Userr userr) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.update(userr);
            session.getTransaction().commit();
        } catch (Exception e) {
            ErrorObj error = new ErrorObj(true, "Ошибка изменения пользователя");
        }
    }

    public static void deleteUser(int Id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.remove(session.get(Userr.class, Id));
            session.getTransaction().commit();
        }
    }

    public static Userr getUser(int Id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Userr.class, Id);
        }
    }


    public static List<Userr> getAllOfUsers() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return loadAllData(Userr.class, session);
        }
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
