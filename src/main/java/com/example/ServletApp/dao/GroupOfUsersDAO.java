package com.example.ServletApp.dao;

import com.example.ServletApp.entities.GroupOfUsers;
import com.example.ServletApp.entities.Groupp;
import com.example.ServletApp.entities.Userr;
import com.example.ServletApp.exception.ErrorObj;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GroupOfUsersDAO {
    public static void insertGroupOfUsers(GroupOfUsers groupOfUsers) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.save(groupOfUsers);
            session.getTransaction().commit();
        }
        catch (Exception e){
            ErrorObj error = new ErrorObj(true,"Ошибка, вы уже подписаны на данную группу");
        }
    }

    public static void updateGroupOfUsers(GroupOfUsers groupOfUsers) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.update(groupOfUsers);
            session.getTransaction().commit();
        }
    }

    public static void deleteGroupOfUsers(Userr user, Groupp group) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.remove(new GroupOfUsers(user, group));
            session.getTransaction().commit();
        }
    }

    public static List<GroupOfUsers> getAllOfGroupOfUsers() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return loadAllData(GroupOfUsers.class, session);
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