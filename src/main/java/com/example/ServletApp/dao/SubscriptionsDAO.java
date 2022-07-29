package com.example.ServletApp.dao;

import com.example.ServletApp.entities.*;
import com.example.ServletApp.exception.ErrorObj;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class SubscriptionsDAO {
    public static void insertSubscriptions(Subscriptions subscriptions) {
        Date date = new Date();
        subscriptions.setSubscriptionDate(new Timestamp(date.getTime()));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.save(subscriptions);
            session.getTransaction().commit();
        } catch (Exception e) {
            ErrorObj error = new ErrorObj(true, "Ошибка, данная подписка уже существует");
        }
    }

    public static void updateSubscriptions(Subscriptions subscriptions) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.update(subscriptions);
            session.getTransaction().commit();
        }
    }

    public static void deleteSubscriptions(int user, int subscriber) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            Query query = session.createQuery("DELETE FROM Subscriptions WHERE (subscriber.userId = :subscriber AND user.userId = :user)");
            query.setParameter("subscriber", subscriber);
            query.setParameter("user", user);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public static List<Subscriptions> getAllOfSubscriptions() {
        List<Subscriptions> subscriptions;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return loadAllData(Subscriptions.class, session);
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
