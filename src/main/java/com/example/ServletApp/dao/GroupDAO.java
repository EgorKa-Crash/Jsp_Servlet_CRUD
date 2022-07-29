package com.example.ServletApp.dao;

import com.example.ServletApp.entities.Groupp;
import com.example.ServletApp.exception.ErrorObj;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class GroupDAO {
    public static void insertGroup(Groupp group) {
        Date date = new Date();
        group.setCreatingDate(new Timestamp(date.getTime()));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.save(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            ErrorObj error = new ErrorObj(true, "Ошибка добавления группы, данное имя группы уже занято");
        }
    }

    public static void updateGroupp(Groupp group) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.update(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            ErrorObj error = new ErrorObj(true, "Ошибка изменения группы, данное имя группы уже занято");
        }
    }

    public static void deleteGroupp(int Id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.remove(session.get(Groupp.class, Id));
            session.getTransaction().commit();
        }
    }

    public static Groupp getGroupp(int Id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Groupp.class, Id);
        }
    }


    public static List<Groupp> getAllOfGroup() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return loadAllData(Groupp.class, session);
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
