package com.example.ServletApp.dao;

import com.example.ServletApp.entities.GroupOfUsers;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GroupOfUsersDAO {
    public static void insertGroupOfUsers(GroupOfUsers groupOfUsers) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(groupOfUsers);
        session.getTransaction().commit();
        session.close();
    }

    public static void updateGroupOfUsers(GroupOfUsers groupOfUsers) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(groupOfUsers);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteGroupOfUsers(int userId, int groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM GroupOfUsers WHERE (userId = :userId AND groupId = :groupId)");
        query.setParameter("userId", userId);
        query.setParameter("groupId", groupId);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static GroupOfUsers getGroupOfUsers(int userId, int groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM GroupOfUsers WHERE (userId = :userId AND groupId = :groupId)");
        query.setParameter("userId", userId);
        query.setParameter("groupId", groupId);
        GroupOfUsers groupOfUsers = (GroupOfUsers)query.list().get(0);//  get(GroupOfUsers.class,Id);

        session.close();
        return groupOfUsers;
    }


    public static List<GroupOfUsers> getAllOfGroupOfUsers() {
        List<GroupOfUsers> groupOfUsers;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        groupOfUsers = loadAllData(GroupOfUsers.class, session);
        session.close();
        return groupOfUsers;
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}