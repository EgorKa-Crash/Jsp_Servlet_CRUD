package com.example.ServletApp.dao;

import com.example.ServletApp.entities.Post;
import com.example.ServletApp.entities.Userr;
import com.example.ServletApp.hibernate.HibernateUtil;
import org.hibernate.Query;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(post);
        session.getTransaction().commit();
        session.close();
    }

    public static void updatePost(Post post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(post);
        session.getTransaction().commit();
        session.close();
    }

    public static void deletePost(int Id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
//        Query query = session.createQuery("DELETE FROM Post WHERE postId = " + Id);
//        query.executeUpdate();
        session.remove(session.get(Post.class,Id));
        session.getTransaction().commit();
        session.close();
    }

    public static Post getPost(int Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Post post = session.get(Post.class,Id);
        session.close();
        return post;
    }


    public static List<Post> getAllOfPost() {
        List<Post> post;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        post = loadAllData(Post.class, session);
        session.close();
        return post;
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}

