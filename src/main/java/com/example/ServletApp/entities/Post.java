package com.example.ServletApp.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Post {
    private Integer postId;
    private String postHeader;
    private Timestamp createDate;
    private String postContent;
    private int userId;

    public Post() {
    }

    public Post(Integer postId, String postHeader, Timestamp createDate, String postContent, int userId) {
        this.postId = postId;
        this.postHeader = postHeader;
        this.createDate = createDate;
        this.postContent = postContent;
        this.userId = userId;
    }

    public Post(String postHeader, String postContent, int userId) {
        this.postHeader = postHeader;
        this.postContent = postContent;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "post_header", nullable = false, length = 50)
    public String getPostHeader() {
        return postHeader;
    }

    public void setPostHeader(String postHeader) {
        this.postHeader = postHeader;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "post_content", nullable = true, length = 500)
    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return userId == post.userId && Objects.equals(postId, post.postId) && Objects.equals(postHeader, post.postHeader) && Objects.equals(createDate, post.createDate) && Objects.equals(postContent, post.postContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postHeader, createDate, postContent, userId);
    }
}
