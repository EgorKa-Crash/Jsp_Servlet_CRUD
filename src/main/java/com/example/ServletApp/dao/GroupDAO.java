package com.example.ServletApp.dao;

import com.example.ServletApp.entities.Groupp;
import com.example.ServletApp.exception.ErrorObj;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class GroupDAO {
    public static void insertGroup(Groupp group) {
        Date date = new Date();
        group.setCreatingDate(new Timestamp(date.getTime()));
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(group);
            session.getTransaction().commit();
        }
        catch (Exception e){
            ErrorObj error = new ErrorObj(true,"Ошибка добавления группы, данное имя группы уже занято");
        }
        session.close();
    }

    public static void updateGroupp(Groupp group) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(group);
            session.getTransaction().commit();
        }
        catch (Exception e){
            ErrorObj error = new ErrorObj(true,"Ошибка изменения группы, данное имя группы уже занято");
        }
        session.close();
    }

    public static void deleteGroupp(int Id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.remove(session.get(Groupp.class,Id));
        session.getTransaction().commit();
        session.close();
    }

    public static Groupp getGroupp(int Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Groupp group = session.get(Groupp.class,Id);
        session.close();
        return group;
    }


    public static List<Groupp> getAllOfGroup() {
        List<Groupp> group;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        group = loadAllData(Groupp.class, session);
        session.close();
        return group;
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
