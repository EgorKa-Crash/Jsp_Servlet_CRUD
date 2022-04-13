package com.example.ServletApp.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer postId;

    @Column(nullable = false, length = 50)
    private String postHeader;

    @Column(nullable = false)
    private Timestamp createDate;

    @Column(length = 500)
    private String postContent;

    @ManyToOne
    @JoinColumn(name = "userId",  nullable = false)
    private Userr user;

    public Post() {
    }

    public Post(String postHeader, String postContent, Userr user) {
        this.postHeader = postHeader;
        this.postContent = postContent;
        this.user = user;
    }

    public Post(Integer postId, String postHeader, Timestamp createDate, String postContent, Userr user) {
        this.postId = postId;
        this.postHeader = postHeader;
        this.createDate = createDate;
        this.postContent = postContent;
        this.user = user;
    }
}
