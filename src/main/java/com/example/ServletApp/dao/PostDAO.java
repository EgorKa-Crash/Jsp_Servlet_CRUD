package com.example.ServletApp.dao;

import com.example.ServletApp.entities.Post;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class PostDAO {
    public static void insertPost(Post post) {
        Date date = new Date();
        post.setCreateDate(new Timestamp(date.getTime()));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.save(post);
            session.getTransaction().commit();
        }
    }

    public static void updatePost(Post post) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.update(post);
            session.getTransaction().commit();
        }
    }

    public static void deletePost(int Id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.remove(session.get(Post.class, Id));
            session.getTransaction().commit();
        }
    }

    public static Post getPost(int Id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Post.class, Id);
        }
    }

    public static List<Post> getAllOfPost() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return loadAllData(Post.class, session);
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

