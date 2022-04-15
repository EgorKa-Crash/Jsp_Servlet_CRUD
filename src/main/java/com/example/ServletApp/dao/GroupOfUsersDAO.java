package com.example.ServletApp.dao;

import com.example.ServletApp.entities.GroupOfUsers;
import com.example.ServletApp.entities.Groupp;
import com.example.ServletApp.entities.Userr;
import com.example.ServletApp.exception.ErrorObj;
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
        try {
            session.save(groupOfUsers);
            session.getTransaction().commit();
        }
        catch (Exception e){
            ErrorObj error = new ErrorObj(true,"Ошибка, вы уже подписаны на данную группу");
        }
        session.close();
    }

    public static void updateGroupOfUsers(GroupOfUsers groupOfUsers) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(groupOfUsers);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteGroupOfUsers(Userr user, Groupp group) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.remove(new GroupOfUsers(user, group));
//        Query query = session.createQuery("DELETE FROM GroupOfUsers WHERE (group.groupId = :group AND user.userId = :user)");
//        query.setParameter("group", group);
//        query.setParameter("user", user);
        //query.executeUpdate();
        session.getTransaction().commit();
        session.close();
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