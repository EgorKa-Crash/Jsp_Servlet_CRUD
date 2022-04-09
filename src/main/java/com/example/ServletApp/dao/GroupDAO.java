package com.example.ServletApp.dao;

import com.example.ServletApp.entities.Groupp;
import com.example.ServletApp.entities.Userr;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO {
    public static void insertGroup(Groupp group) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(group);
        session.getTransaction().commit();
        session.close();
    }

    public static void updateGroupp(Groupp group) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(group);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteGroupp(int Id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Groupp WHERE groupId = " + Id);
        query.executeUpdate();
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
        //users.stream().forEach(x -> System.out.println(x.toString()));
        session.close();
        return group;

//        List<Groupp> group;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
//        Query query = session.createQuery("FROM Groupp");
//        group = query.list();
//        session.close();
//        return group;

//        List list = getSessionFactory().getCurrentSession().createQuery("from Group").list();
//        return (List<Group>) list;
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
